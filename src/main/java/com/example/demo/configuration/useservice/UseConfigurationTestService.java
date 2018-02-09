package com.example.demo.configuration.useservice;

import com.example.demo.configuration.servcie.ConfigurationTestService;

/**
 * Created by liuyumeng on 2018/2/9.
 */
public class UseConfigurationTestService {
    public ConfigurationTestService configurationTestService;

    public void setConfigurationTestService(ConfigurationTestService configurationTestService) {
        this.configurationTestService = configurationTestService;
    }

    public void use(){
        configurationTestService.f1();
    }
}
