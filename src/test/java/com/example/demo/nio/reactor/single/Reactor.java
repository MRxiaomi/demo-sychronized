package com.example.demo.nio.reactor.single;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by liuyumeng on 2018/9/21.
 * <p>
 * reactor 模式：
 * NioServer已经接近reactor模式，但是可以在扩展性上可以提升
 * <p>
 * ** 角色  ******  作用
 * ** Reactor  **   将I/O事件分派给对应的Handler
 * ** Acceptor **   接收连接，分派连接到处理器链
 * ** Handlers **   执行非阻塞读写任务
 */
public class Reactor implements Runnable {
    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    public Reactor(int port) {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));

            selector = Selector.open();

            serverSocketChannel.configureBlocking(false);
            //Acceptor对连接进行处理
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, new Acceptor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                while (it.hasNext()) {
                    dispatcher((SelectionKey) it.next());
                    it.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void dispatcher(SelectionKey selectionKey) {
        //获取指定的handler 连接事件->Acceptor  读写事件->Handlers
        Runnable runnable = (Runnable) selectionKey.attachment();
        if (runnable!=null){
            runnable.run();
        }
    }

    class Acceptor implements Runnable {
        //Acceptor对连接进行处理
        @Override
        public void run() {
            try {
                //获取连接
                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println("接收连接...");
                //分派处理器
                new Handler(socketChannel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Handler implements Runnable {
        private static final int READING = 0, WRITING = 1;

        private SocketChannel socketChannel;

        private int state;

        private SelectionKey selectionKey;

        public Handler(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
            try {
                socketChannel.configureBlocking(false);//和下面一行顺序不能颠倒
                selectionKey = socketChannel.register(selector, SelectionKey.OP_READ, this);
                state = READING;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                if (state == READING) {
                    read();
                } else if (state == WRITING) {
                    write();
                }
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            }
        }

        private void read() throws ClosedChannelException {
            process();
            System.out.println("读操作...");
            //下一步处理写事件
            //selectionKey.interestOps(SelectionKey.OP_WRITE);
            socketChannel.register(selector, SelectionKey.OP_WRITE,this);
            this.state = WRITING;
        }

        private void write() throws ClosedChannelException {
            process();
            System.out.println("写操作...");
            //下一步处理读事件
            //selectionKey.interestOps(SelectionKey.OP_READ);
            socketChannel.register(selector, SelectionKey.OP_READ);
            this.state = READING;
        }

        /**
         * task 业务处理
         */
        private void process() {
            //do something
        }
    }
}
