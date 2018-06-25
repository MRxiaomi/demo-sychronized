package com.example.demo.java8.stream;

import java.util.ArrayList;

/**
 * Created by liuyumeng on 2018/6/25.
 *
 * parallelStream 多线程并行处理，打印结果顺序不固定
 */
public class ParallelDemo {
    //public volatile AtomicInteger race = new AtomicInteger(0);

    public static void main(String[] args) {
       new ArrayList<String>(){{
            add("dubbo");
            add("springcloud");
           add("springmvc");
        }}.parallelStream().forEach((a)-> System.out.println("当前线程:"+Thread.currentThread()+ " "+a));
    }
}
