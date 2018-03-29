package com.example.demo.jsr133;

/**
 * Created by liuyumeng on 2018/3/18.
 */
public class RecorderExample {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;  //  <span style="color:#ff0000;">1指令</span>
        ready = true;  // <span style="color:#ff0000;">2指令</span>
    }
}
