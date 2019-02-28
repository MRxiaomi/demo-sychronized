package com.example.demo.collections;

import java.util.HashMap;

/**
 * @author liuyumeng
 */
public class HashMapThreadSafe {
    private static HashMap<Integer, Object> hashMap = new HashMap<>();

    public static void main(String[] args) {
        new Thread(new Put()).start();
        new Thread(new Put()).start();
        new Thread(new Put()).start();
        new Thread(new Put()).start();
        new Thread(new Put()).start();

        new Thread(new Get()).start();
        new Thread(new Get()).start();
        new Thread(new Get()).start();
        new Thread(new Get()).start();
        new Thread(new Get()).start();
    }

    static class Put implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                hashMap.put(i, i);
               // System.out.println(Thread.currentThread() + " put:" + i);
            }
            System.out.println(Thread.currentThread());
        }
    }

    static class Get implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000000; i++) {
                if (hashMap.get(i) == null){
                    System.out.println(Thread.currentThread() + " get null");
                }
            }
            System.out.println(Thread.currentThread());
        }
    }
}
