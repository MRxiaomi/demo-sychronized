package com.example.demo.aop;

import com.example.demo.GlobalConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by liuyumeng on 2018/2/9.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(GlobalConfiguration.class);
        AopAnnotationService aopAnnotation = annotationConfigApplicationContext.getBean(AopAnnotationService.class);
        AopMethodService aopMethod = annotationConfigApplicationContext.getBean(AopMethodService.class);

        aopAnnotation.add();
        aopMethod.add();
    }
}
