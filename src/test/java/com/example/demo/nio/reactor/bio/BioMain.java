package com.example.demo.nio.reactor.bio;

/**
 * Created by liuyumeng on 2018/9/21.
 */
public class BioMain {
    public static void main(String[] args) {
        BioServer bioServer = new BioServer(8900);
        bioServer.run();
    }
}
