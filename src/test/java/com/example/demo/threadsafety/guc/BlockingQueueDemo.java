package com.example.demo.threadsafety.guc;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuyumeng on 2018/4/20.
 */
public class BlockingQueueDemo {
    private static LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

    private static volatile boolean running = true;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    linkedBlockingQueue.put("1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Runnable runnable = new Consumer();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    static class Consumer implements Runnable {
        public Consumer() {
        }

        @Override
        public void run() {
            while (running) {
                String s = null;
                try {
                    s = linkedBlockingQueue.poll(10, TimeUnit.SECONDS);
                    if (s != null) System.out.println("当前线程:" + s);
                    if (s == null) {
                        running = false;
                        System.out.println("当前线程:" + false);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("当前线程结束:" + Thread.currentThread());
        }
    }
}
