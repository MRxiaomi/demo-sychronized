package com.example.demo.threadsafety.sync;

/**
 * Created by liuyumeng on 2018/1/20.
 */
public class AccountingSyncBad implements Runnable{
    static int i=0;

    /**
     * 作用于静态方法,锁是当前class对象,也就是
     * AccountingSyncBad类对应的class对象
     */
    public static synchronized   void increase(){
        i++;
    }

    public   synchronized void increase4Ob(){
        i++;
    }

    @Override
    public void run() {
        for(int j=0;j<1000000;j++){
            increase();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new AccountingSyncBad());
        Thread t2=new Thread(new AccountingSyncBad());

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(i);
    }


}
