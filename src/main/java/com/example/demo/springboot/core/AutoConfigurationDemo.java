package com.example.demo.springboot.core;

/**
 * Created by liuyumeng on 2018/2/9.
 */
public class AutoConfigurationDemo {
    private String msg;

    public void f1(){
        System.out.printf("AutoConfigurationDemo.class "+msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
