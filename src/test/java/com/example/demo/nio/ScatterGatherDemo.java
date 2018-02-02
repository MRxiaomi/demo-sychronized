package com.example.demo.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;

/**
 * Created by liuyumeng on 2018/2/2.
 */
public class ScatterGatherDemo {
    private static Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");//其他方式？
        ScatteringByteChannel inChannel = aFile.getChannel();

        ByteBuffer byteBuffer1 = ByteBuffer.allocate(128);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);

        ByteBuffer[] buffers = {byteBuffer1, byteBuffer2};

        inChannel.read(buffers);

        byteBuffer1.flip();
        //head缓冲区中的数据:qw
        System.out.println("head缓冲区中的数据:" + charset.decode(byteBuffer1));

        byteBuffer1.flip();
        //body缓冲区中的数据:ertyuiop //todo 这块数据会乱码--
        System.out.println("body缓冲区中的数据:" + charset.decode(byteBuffer2));

    }
}

