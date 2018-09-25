package com.example.demo.disruptor;

/**
 * @author liuyumeng
 *         <p>
 *         利用缓存行和不利用缓存行的性能对比
 *
 *
 *         Loop times:23ms
 *         Loop times:64ms
 */
public class CacheLineTest {
    //考虑一般缓存大小是64字节 一个long占8字节
    static long[][] arr;

    public static void main(String[] args) {
        //初始化1024*1024行的二维数组
        arr = new long[1024 * 1024][];

        for (int i = 0; i < 1024 * 1024; i++) {
            //每一行为8位的long数组，刚好一个cacheline大小
            arr[i] = new long[8];
            for (int j = 0; j < 8; j++) {
                arr[i][j] = 0L;
            }
        }


        long sum = 0L;
        //按行读取-命中缓存
        long marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i += 1) {
            for (int j = 0; j < 8; j++) {
                sum = arr[i][j];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");

        //按列读取-不命中缓存
        marked = System.currentTimeMillis();
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 1024 * 1024; j++) {
                sum = arr[j][i];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");
    }
}
