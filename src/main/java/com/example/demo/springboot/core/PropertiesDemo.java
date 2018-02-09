package com.example.demo.springboot.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by liuyumeng on 2018/2/9.
 *
 * todo  bookName=speing boot å®æ
 */
@RestController
@SpringBootApplication
public class PropertiesDemo {
    @Value("${book.name}")
    private String bookName;

    @RequestMapping("/")
    public void value(){
        System.out.printf("bookName="+bookName);
    }

    @Resource
    private AutoConfigurationDemo autoConfigurationDemo;

    @RequestMapping("/autoConfiguration")
    public void autoConfiguration(){
        autoConfigurationDemo.f1();
    }
}
