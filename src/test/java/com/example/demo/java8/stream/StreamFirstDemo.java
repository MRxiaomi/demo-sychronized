package com.example.demo.java8.stream;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by liuyumeng on 2018/6/25.
 *
 * 包含整数的集合中筛选出所有的偶数
 */
public class StreamFirstDemo {
    public static void main(String[] args) {
        new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }}.stream().filter((x) -> x % 2 == 0).collect(Collectors.toSet())
                .forEach(x -> System.out.println("测试 stream " + x));
    }
}
