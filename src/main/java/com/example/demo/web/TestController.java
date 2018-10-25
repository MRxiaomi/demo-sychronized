package com.example.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyumeng on 2018/10/24.
 */
@RestController
public class TestController {
    @GetMapping("/test/1001")
    public String test1(){
        return "success";
    }
}
