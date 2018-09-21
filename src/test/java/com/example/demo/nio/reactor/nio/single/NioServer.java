package com.example.demo.nio.reactor.nio.single;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author liuyumeng
 */
public class NioServer extends Thread {
    private int port;

    //使用nio的ServerSocketChannel封装Socket
    private ServerSocketChannel serverSocketChannel;

    //全局的selector 用于监听channel上的事件
    private Selector selector;

    public NioServer(int port) {
        this.port = port;
        try {
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();

            //监听具体端口
            serverSocketChannel.socket().bind(new InetSocketAddress(port));

            //非阻塞方式
            serverSocketChannel.configureBlocking(false);

            //将channel 注册到selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                //阻塞的等待事件
                selector.select();

                // 事件列表
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()) {
                    //分发事件
                    dispatch((SelectionKey) (it.next()));
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey selectionKey) {
        try {
            if (selectionKey.isAcceptable()) {
                new Regist(selectionKey).start();//新链接建立，注册
            } else if (selectionKey.isReadable()) {
                new Read(selectionKey).start();//读事件处理
            } else if (selectionKey.isWritable()) {
                new Read(selectionKey).start();//写事件处理
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    class Regist extends Thread {
        SelectionKey key;

        public Regist(SelectionKey key) {
            this.key = key;
        }

        public void run() {
            try {
                ServerSocketChannel server = (ServerSocketChannel) key
                        .channel();
                // 获得和客户端连接的通道
                SocketChannel channel = null;
                channel = server.accept();

                channel.configureBlocking(false);
                //客户端通道注册到selector 上
                channel.register(selector, SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Read extends Thread {
        SelectionKey key;

        public Read(SelectionKey key) {
            this.key = key;
        }

        private void run(SelectionKey key) throws IOException {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key
                    .channel();
            SocketChannel channel = serverSocketChannel.accept();

            channel.register(selector, SelectionKey.OP_WRITE);
        }
    }

    class Write extends Thread {
        SelectionKey key;

        public Write(SelectionKey key) {
            this.key = key;
        }

        private void run(SelectionKey key) throws IOException {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key
                    .channel();
            SocketChannel channel = serverSocketChannel.accept();

            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.put("hello mi".getBytes("UTF-8"));
            channel.write(buf);
            //客户端通道注册到selector 上
            channel.close();
        }
    }
}
