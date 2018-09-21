package com.example.demo.nio.reactor.multithreadhandler;

/**
 * Created by liuyumeng on 2018/9/21.
 */
public class ReactorMain {
    public static void main(String[] args) {
        Reactor reactor = new Reactor(8900);
        reactor.run();
    }
}
