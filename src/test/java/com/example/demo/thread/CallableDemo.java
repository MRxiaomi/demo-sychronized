package com.example.demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.*;

/**
 * Created by liuyumeng on 2018/6/30.
 */
public class CallableDemo implements Callable {
    @Override
    public Object call() throws Exception {
        int amount = 0;
        for (int i = 0; i < 10; i++) {
            amount++;
            System.out.println(amount);
        }
        return amount;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallableDemo());
        executorService.submit(futureTask);

        try {
            Thread.sleep(200);
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
