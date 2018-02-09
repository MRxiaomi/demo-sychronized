package com.example.demo.configuration.mains;

import com.example.demo.GlobalConfiguration;
import com.example.demo.configuration.servcie.ConfigurationTestService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by liuyumeng on 2018/2/9.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(GlobalConfiguration.class);
        ConfigurationTestService configurationTestService = annotationConfigApplicationContext.getBean(ConfigurationTestService.class);
        configurationTestService.f1();
    }
}
