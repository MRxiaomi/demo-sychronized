package com.example.demo.lock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by liuyumeng on 2018/1/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo2 {
    @Test
    public void demo1() {
        String s = "s";
        synchronized (s) {
            doSomething();
        }
    }

    private void doSomething(){}

}
