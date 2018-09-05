package com.example.demo.proxy.jdk;

/**
 * Created by liuyumeng on 2018/8/15.
 */
public class SubjectImpl implements Subject{
    @Override
    public void request() {
        System.out.println("SubjectImpl 执行 ....");
    }
}
