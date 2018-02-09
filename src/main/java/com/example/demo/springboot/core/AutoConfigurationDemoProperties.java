package com.example.demo.springboot.core;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by liuyumeng on 2018/2/9.
 *
 * 根据此类来判断是否创建该类的Bean，可以是第三方类
 */
@ConfigurationProperties(prefix = "auto.configuration.demo")
public class AutoConfigurationDemoProperties {
    public static final String MSG = "hello";

    //可以通过application.properties来设置，不设置为默认值
    private String msg = MSG;

    public static String getMSG() {
        return MSG;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
