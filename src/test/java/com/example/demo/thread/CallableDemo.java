package com.example.demo.thread;

import java.util.ArrayList;
import java.util.List;
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
        //testFutureTask();

        //testFutureTask2();

        //testFeature();

        testRunnabel();
    }

    private static void testFutureTask() {
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

    /**
     * runnable和Callable之间可以通过RunnableFuture相互转换，FutureTask是其实现类
     * <p>
     * futureTask.get();任务结束之前会阻塞
     */
    private static void testFutureTask2() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallableDemo());
        executorService.execute(futureTask);
        try {
            futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * future.get();阻塞地等待任务执行完成
     */
    private static void testFeature() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(new CallableDemo());

        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    private static volatile List<Integer> result = new ArrayList<>();

    static {
        result.add(0, 0);
    }


    /**
     * //此处的result 在传入时候已经固定了。
     *
     * 如果要 动态获取值，可以传集合类或具体对象；否则可以用FutureTask
     */
    private static void testRunnabel() {
        Callable<List<Integer>> callable = Executors.callable(new Runnable() {
            @Override
            public void run() {
                result.add(0, result.get(0) + 1);
            }
        }, result);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<List<Integer>> future = executorService.submit(callable);

        try {
            System.out.printf("结果：" + future.get().get(0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
