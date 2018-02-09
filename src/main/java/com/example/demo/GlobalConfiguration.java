package com.example.demo;

import com.example.demo.configuration.servcie.ConfigurationTestService;
import com.example.demo.configuration.useservice.UseConfigurationTestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by liuyumeng on 2018/2/9.
 *
 * 本项目使用java配置和注解共同使用,java配置是使用Configuration+Bean,@Bean放在方法上，声明当前方法返回一个Bean
 * 2.
 */
@Configuration
@ComponentScan("com.example.demo")
@EnableAspectJAutoProxy
public class GlobalConfiguration {
    //ConfigurationTestService-使用功能类的Bean
    @Bean
    public ConfigurationTestService configurationTestService(){
        return new ConfigurationTestService();
    }

    @Bean
    public UseConfigurationTestService useConfigurationTestService(){
        UseConfigurationTestService useConfigurationTestService = new UseConfigurationTestService();
        //依赖注入
        useConfigurationTestService.setConfigurationTestService(configurationTestService());
        return useConfigurationTestService;
    }

}