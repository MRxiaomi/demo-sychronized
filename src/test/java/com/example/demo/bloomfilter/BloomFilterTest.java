package com.example.demo.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;
import java.util.Random;

/**
 * Created by liuyumeng on 2018/9/5.
 */
public class BloomFilterTest {
    private static final int SIZE = 100_0000;

    private static Random random = new Random();


    public static void main(String[] args) {
        defaultMethod();
        newMethod();
        stringMethod();
    }

    private static void defaultMethod() {
        int count = 0;
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), SIZE);
        for (int i = 0; i < 1000000; i++) {
            bloomFilter.put(i);
        }
        if (bloomFilter.mightContain(1)) {
            System.out.println("包含值：" + 1);
        }
        //误伤率 = 30155/1000000 ==> 3%
        for (int i = 1000000; i < 2000000; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
            }
        }
        System.out.println("defaultMethod误伤数：" + count);
    }

    /**
     * 3% 误伤率过高 降低误伤率
     */
    private static void newMethod() {
        int count = 0;
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), SIZE, 0.0001);
        for (int i = 0; i < 1000000; i++) {
            bloomFilter.put(i);
        }
        //误伤率 = 30155/1000000 ==> 3%
        for (int i = 1000000; i < 2000000; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
            }
        }
        System.out.println("newMethod误伤数：" + count);
    }

    private static void stringMethod() {
        int count = 0;
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), SIZE, 0.0001);
        for (int i = 0; i < 1000000; i++) {
            bloomFilter.put("中文&english-" + i);
        }
        for (int i = 1000000; i < 2000000; i++) {
            if (bloomFilter.mightContain("中文&english--" + i)) {
                count++;
            }
        }
        System.out.println("stringMethod误伤数：" + count);
    }
}
