package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liuyumeng on 2018/1/20.
 */
public class Demo3 implements Runnable {
    static AtomicInteger race = new AtomicInteger(0);

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            race.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Demo3());
        Thread t2 = new Thread(new Demo3());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(race.get());
    }
}
