package com.example.demo.nio.reactor.bio.single;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.charset.Charset;

/**
 * @author liuyumeng
 *
 *         单线程模型
 *
 *         仅有一个线程
 *
 *         只能处理一个请求
 *
 *         无法利用多核优势
 *
 *
 */
public class BioServer implements Runnable {
    private int port;

    private ServerSocket serverSocket;

    public BioServer(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    public void run() {
        try {
            while (true) {
                //连接操作
                java.net.Socket socket = serverSocket.accept();

                //read操作
                InputStream inputStream = socket.getInputStream();

                System.out.println("输入...");
                //write操作
                OutputStream outputStream = socket.getOutputStream();
                System.out.println("输出...");

                outputStream.write("abc".getBytes(Charset.forName("UTF-8")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
