package com.example.demo.thread;

/**
 * Created by liuyumeng on 2018/11/23.
 */
public class Join {
    private static int current = 0;

    private static final String LOCK_STRING = "LOCK_";

    public static void main(String[] args) {
        new Thread(new JoinThread(0)).start();
        new Thread(new JoinThread(1)).start();
        new Thread(new JoinThread(2)).start();
    }

    static class JoinThread implements Runnable {
        private int threadNo;

        public JoinThread(int threadNo) {
            this.threadNo = threadNo;
        }

        @Override
        public void run() {
            //每个线程轮询获取锁
            while (true) {
                synchronized (LOCK_STRING) {
                    //不属于当前线程执行->阻塞
                    while (current % 3 != threadNo) {
                        try {
                            //重点***===释放CPU执行权、释放锁->进入lock pool，等待notify/notifyAll***===//
                            LOCK_STRING.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    //属于自己执行 超过最大值，跳出循环
                    if (current > 100) {
                        break;
                    }
                    System.out.println("thread-" + threadNo + " : " + current);
                    current++;
                    // 唤醒其他wait线程
                    LOCK_STRING.notifyAll();
                }
            }
        }
    }
}
