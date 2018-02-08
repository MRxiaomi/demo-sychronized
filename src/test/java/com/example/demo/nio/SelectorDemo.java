package com.example.demo.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by liuyumeng on 2018/2/8.
 * <p>
 * Selector（选择器）是Java NIO中能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。
 * <p>
 * 本例测试单线程使用一个Selector处理3个channel
 */
public class SelectorDemo {
    public static void main(String[] args) throws Exception {
        //1.创建一个Selector
        Selector selector = Selector.open();

        //2.注册通道
        //与Selector一起使用时，Channel必须处于非阻塞模式下。这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以。
//        RandomAccessFile from = new RandomAccessFile("data/from.txt","rw");
//        FileChannel fileChannel = from.getChannel();
        SocketChannel socketChannel1 = SocketChannel.open();
        socketChannel1.configureBlocking(false);
        socketChannel1.register(selector, SelectionKey.OP_READ);
        socketChannel1.connect(new InetSocketAddress("192.168.200.244", 80));

        SocketChannel socketChannel2 = SocketChannel.open();
        socketChannel2.configureBlocking(false);
        socketChannel2.register(selector, SelectionKey.OP_READ);
        socketChannel2.connect(new InetSocketAddress("192.168.200.244", 80));

        SocketChannel socketChannel3 = SocketChannel.open();
        socketChannel3.configureBlocking(false);
        socketChannel3.register(selector, SelectionKey.OP_READ);
        socketChannel3.connect(new InetSocketAddress("192.168.200.244", 80));


        //todo 这里为什么一直接取不到？
        if (selector.select() > 0) {
            Set keys = selector.selectedKeys();
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();
                if (key.isAcceptable()) System.out.println("isAcceptable");
                if (key.isConnectable()) System.out.println("isConnectable");
                if (key.isReadable()) System.out.println("isReadable");
                if (key.isValid()) System.out.println("isValid");
                if (key.isWritable()) System.out.println("sWritable");
                iterator.remove();
            }
        }


        //关闭socketChannel
        socketChannel1.close();
        socketChannel2.close();
        socketChannel3.close();
    }
}
