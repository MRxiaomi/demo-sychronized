package com.example.demo.threadsafety.guc;

/**
 * Created by liuyumeng on 2018/8/15.
 *
 * wait 必须在sychronized中使用，否则报错：
 * Exception in thread "main" java.lang.IllegalMonitorStateException
 at java.lang.Object.wait(Native Method)
 at java.lang.Object.wait(Object.java:502)
 at com.example.demo.threadsafety.guc.WaitDemo.main(WaitDemo.java:9)
 */
public class WaitDemo {
    public static void main(String[] args) throws InterruptedException {
        String s =  new String("lock_string");
        //s.wait();
        synchronized (s){
            System.out.println("线程进入监视器");
            s.wait();
            System.out.println("线程重新获取监视器");
        }
    }
}
