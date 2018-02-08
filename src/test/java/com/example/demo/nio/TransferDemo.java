package com.example.demo.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by liuyumeng on 2018/2/8.
 *  1.FileChannel的transferFrom()方法可以将数据从源通道传输到FileChannel中
 *  2.
 */
public class TransferDemo {
    public static void main(String[] args) throws Exception {
//        RandomAccessFile from = new RandomAccessFile("data/from.txt","rw");
//        RandomAccessFile to = new RandomAccessFile("data/to.txt","rw");
//
//        FileChannel fromChannel = from.getChannel();
//        FileChannel toChannel = to.getChannel();
//
//        toChannel.transferFrom(fromChannel,0, fromChannel.size());

        //貌似修改不了
        RandomAccessFile from = new RandomAccessFile("data/from.txt","rw");
        RandomAccessFile to = new RandomAccessFile("data/to.txt","rw");

        FileChannel fromChannel = from.getChannel();
        FileChannel toChannel = to.getChannel();

        fromChannel.transferTo(0, fromChannel.size(),toChannel);
    }
}
