package com.example.demo.aop.log;

import org.springframework.stereotype.Service;

/**
 * Created by liuyumeng on 2018/2/9.
 * 根据注解进行拦截的类
 */
@Service
public class AnnotationService {
    @Log(ignore = false)
    public void  add(){
        System.out.println("AopAnnotationService add()");
    }
}
