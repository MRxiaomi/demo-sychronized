package com.example.demo.aop;

import org.springframework.stereotype.Service;

/**
 * Created by liuyumeng on 2018/2/9.
 *
 * 根据方法规则进行拦截的类
 */
@Service
public class AopMethodService {
    public void  add(){
        System.out.println("AopMethodService add()");
    }
}
