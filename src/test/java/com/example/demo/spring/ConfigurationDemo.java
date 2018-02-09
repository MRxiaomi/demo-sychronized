package com.example.demo.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by liuyumeng on 2018/2/9.
 */
public class ConfigurationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfigure.class);
        ServiceDemo serviceDemo = annotationConfigApplicationContext.getBean(ServiceDemo.class);
        serviceDemo.service1();
    }
}
