package com.example.demo.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by liuyumeng on 2018/2/2.
 */
public class FileChannelDemo {
    public static void main(String []args) throws Exception{
        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");//其他方式？
        FileChannel inChannel = aFile.getChannel();

        //1.create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);

        //2.写入数据到buffer
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            //3.注意 buf.flip() 的调用，将Buffer从写模式切换到读模式
            buf.flip();
            while(buf.hasRemaining()){
                //4.读取之前写入到buffer的所有数据
                System.out.print((char) buf.get());
            }
            //5.clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();

    }
}
