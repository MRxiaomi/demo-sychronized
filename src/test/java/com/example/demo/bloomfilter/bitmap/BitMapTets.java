package com.example.demo.bloomfilter.bitmap;

/**
 * Created by liuyumeng on 2018/9/4.
 * 优点：
 * <p>
 * 1.运算效率高，不许进行比较和移位；
 * <p>
 * 2.占用内存少，比如N=10000000；只需占用内存为N/8=1250000Byte=1.25M。
 * 缺点：
 * <p>
 * 所有的数据不能重复。即不可对重复的数据进行排序和查找。
 */
public class BitMapTets {
    public static void main(String[] args) {
        int[] arrays = new int[]{5, 7, 1, 2};
        sort(arrays);
    }

    private static void sort(int[] arrays) {
        //假设已知道最大的数不超过8
        int max = 8;
        int result = 0;
        for (int array : arrays) {
            result = result | (0x01 << (array % max));
        }
        System.out.println(result);//166 -> 10100110 1，2，5，7正好是排过序的

        for (int i = 0; i < max; i++) {
            if ((0x01 << i & result) != 0) {
                System.out.println(i);
            }
        }
    }
}
