package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


/**
 * 可以关闭特定的配置 例:
 *
 * @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
 */
@SpringBootApplication
public class SpringbootdemosApplication {

    public static void main(String[] args) {
        System.out.println("正在启动。。。");
        new SpringApplicationBuilder(SpringbootdemosApplication.class)
                .run(args);
    }
}
