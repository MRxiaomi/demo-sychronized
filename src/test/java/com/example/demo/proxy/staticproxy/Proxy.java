package com.example.demo.proxy.staticproxy;

/**
 * Created by liuyumeng on 2018/8/15.
 *
 * 需要实现代理，只需要传入一个实现类subjectImpl即可
 *
 * 缺陷：对于不通类型的Subject，需要实现不通的代理类，不易维护
 */
public class Proxy implements Subject{
    private Subject subjectImpl;

    public Proxy(Subject subjectImpl) {
        subjectImpl = subjectImpl;
    }

    @Override
    public void request() {
        System.out.println("do st...");
        subjectImpl.request();
        System.out.println("do st...");
    }
}
