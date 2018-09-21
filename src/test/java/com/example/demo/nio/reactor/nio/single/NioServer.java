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
 *
 * 基于NIO的单线程reactor
 *
 * 1.非阻塞IO读写
 * 2.基于IO事件分发任务
 *
 *
 * reactor相关：
 *    1.基于事件驱动-> selector(可监听多个socketChannel)
 *    2.统一事件分派->dispatcher
 *    3.事件处理服务 read&write
 *
 * 解决的问题：
 *      1.一个线程维护多个客户端（socketChannel)
 *      2.非阻塞
 *
 * 缺点：
 *     个人认为：事件处理服务 和 连接的建立 可以使用连接池，提高并发处理能力
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
            //OP_ACCEPT：接受就绪：准备好接受新进入的连接
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
                regist(selectionKey);//新链接建立，注册
            } else if (selectionKey.isReadable()) {
                read(selectionKey);//读事件处理
            } else if (selectionKey.isWritable()) {
                write(selectionKey);//写事件处理
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void regist(SelectionKey key) {
        try {
            ServerSocketChannel server = (ServerSocketChannel) key
                    .channel();
            // 获得和客户端连接的通道
            SocketChannel channel = null;
            channel = server.accept();

            channel.configureBlocking(false);
            //客户端通道注册到selector 上
            //OP_READ:读就绪
            channel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key
                .channel();

        System.out.println("读取数据");

        //OP_WRITE:写就绪
        channel.register(selector, SelectionKey.OP_WRITE);
    }

    private void write(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key
                .channel();

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.put("hello mi".getBytes("UTF-8"));
        channel.write(buf);
        //客户端通道注册到selector 上
        //channel.close();
        channel.register(selector, SelectionKey.OP_READ);
    }
}
