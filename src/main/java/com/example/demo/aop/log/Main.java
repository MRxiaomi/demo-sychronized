package com.example.demo.aop.log;

import com.example.demo.AutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by liuyumeng on 2018/2/9.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AutoConfiguration.class);
        AnnotationService annotationService = annotationConfigApplicationContext.getBean(AnnotationService.class);

        annotationService.add();
    }
}
