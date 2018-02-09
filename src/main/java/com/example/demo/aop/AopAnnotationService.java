package com.example.demo.aop;

import org.springframework.stereotype.Service;

/**
 * Created by liuyumeng on 2018/2/9.
 * 根据注解进行拦截的类
 */
@Service
public class AopAnnotationService {
    @Action(name = "注解式拦截的add操作")
    public void  add(){
        System.out.println("AopAnnotationService add()");
    }
}
