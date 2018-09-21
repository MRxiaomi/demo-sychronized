package com.example.demo.nio.reactor.nio.single;

/**
 * Created by liuyumeng on 2018/9/21.
 */
public class NioMain {
    public static void main(String[] args) {
        NioServer nioServer = new NioServer(8900);
        nioServer.run();
    }
}
