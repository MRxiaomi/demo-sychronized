package com.example.demo.thread;

/**
 * Created by liuyumeng on 2018/11/23.
 *
 * IllegalMonitorStateException:
 * 1>当前线程不含有当前对象的锁资源的时候，调用obj.wait()方法;
  2>当前线程不含有当前对象的锁资源的时候，调用obj.notify()方法。
  3>当前线程不含有当前对象的锁资源的时候，调用obj.notifyAll()方法。
 */
public class TestWait {
    private static final String LOCK_STRING = "1";

    public static void main(String[] args) {
        new Thread(new JoinThread(1)).start();
    }

    static class JoinThread implements Runnable {
        private int threadNo;

        public JoinThread(int threadNo) {
            this.threadNo = threadNo;
        }

        @Override
        public void run() {
            //每个线程轮询获取锁
//            synchronized (LOCK_STRING) {
//
//            }
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
