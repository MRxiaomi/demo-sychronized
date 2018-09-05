package com.example.demo.thread;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuyumeng on 2018/8/20.
 *
 * 没两秒钟执行一次定时任务
 */
public class ThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(2);

        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

            }
        },2,2, TimeUnit.SECONDS);
    }
}
