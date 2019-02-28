package com.example.demo.proxy.jdk;

/**
 * Created by liuyumeng on 2018/8/15.
 */
public class SubjectImpl implements Subject{
    public SubjectImpl() {
    }

    @Override
    public void request() {
        System.out.println("SubjectImpl 执行 ....");
    }
}

class test extends SubjectImpl{
    public test() {
        super();
    }
}
