package com.example.demo.springboot.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyumeng on 2018/2/9.
 */

@RestController
public class TypeSafetyPropertiesCntroller {
    @Autowired
    private TypeSafetyPropertiesDemo typeSafetyPropertiesDemo;

    @RequestMapping("/typeSafetyProperties")
    public void value(){
        System.out.printf("bame="+typeSafetyPropertiesDemo.getName());
        System.out.printf("age="+typeSafetyPropertiesDemo.getAge());
    }
}
