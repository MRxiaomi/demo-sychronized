package com.example.demo.springboot.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by liuyumeng on 2018/2/9.
 */
@Component
@ConfigurationProperties(prefix = "author")
@PropertySource("classpath:/typeSafetyApplication.propertise")
public class TypeSafetyPropertiesDemo {
    private String name;

    private Long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
