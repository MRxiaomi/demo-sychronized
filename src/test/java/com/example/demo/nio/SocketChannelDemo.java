package com.example.demo.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by liuyumeng on 2018/2/8.
 */
public class SocketChannelDemo {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("192.168.200.244", 80));



        ByteBuffer buf = ByteBuffer.allocate(48);

        //todo 为啥读不到数据呢?
        int i = socketChannel.read(buf);
        //while (socketChannel.read(buf) == -1) {
            buf.flip();
            while(buf.hasRemaining()){
                //4.读取之前写入到buffer的所有数据
                System.out.print((char) buf.get());
            }
        //}

        socketChannel.close();

    }
}
