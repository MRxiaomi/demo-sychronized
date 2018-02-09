package com.example.demo.nio;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by liuyumeng on 2018/2/8.
 */
public class ServerSocketDemo {
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));

        //非阻塞模式
        //serverSocketChannel.configureBlocking(false);

        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");//其他方式？
        FileChannel inChannel = aFile.getChannel();

        //1.create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);

        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();

            //如果为非阻塞，需要判空
            System.out.println("do st for socketChannel");
            socketChannel.write(buf);
        }
    }
}
