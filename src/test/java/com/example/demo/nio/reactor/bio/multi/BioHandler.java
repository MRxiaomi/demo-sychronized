package com.example.demo.nio.reactor.bio.multi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by liuyumeng on 2018/9/21.
 */
public class BioHandler extends Thread {
    private Socket socket;

    public BioHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //read操作
            InputStream inputStream = socket.getInputStream();
            System.out.println("输入...");
            //write操作
            OutputStream outputStream = socket.getOutputStream();
            System.out.println("输出...");

            outputStream.write("abc".getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
