package com.example.demo.java8.stream;

import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by liuyumeng on 2018/11/9.
 */
public class OptionalTest {
    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9);

        Optional<List<Integer>> optional = Optional.of(list).map(l -> list.stream().filter(i->i>5).collect(Collectors.toList()));

        System.out.printf(".");
    }
}
