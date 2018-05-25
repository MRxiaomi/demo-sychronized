package com.example.demo.jsr133;

/**
 * Created by liuyumeng on 2018/28.
 */
public class VolatileDemo {
    private volatile int i;

    private void add() {
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        final VolatileDemo demo = new VolatileDemo();
        for (int n = 0; n < 1000; n++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);//制造并发
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    demo.add();
                }
            }).start();
        }

        //等待10秒，保证上面程序执行完成
        Thread.sleep(10000L);
        System.out.println(demo.i);
    }


}
