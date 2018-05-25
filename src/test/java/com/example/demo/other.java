package com.example.demo;

/**
 * Created by liuyumeng on 2018/5/24.
 */
public class other {
    public static void main(String[] args) {
        System.out.println("+："+((Long.valueOf(Integer.MAX_VALUE) << 32) + Integer.MAX_VALUE));
        System.out.println("|："+((Long.valueOf(Integer.MAX_VALUE) << 32) |  Integer.MAX_VALUE));
    }
}
