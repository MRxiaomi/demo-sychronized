package com.example.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 可以关闭特定的配置 例:
 *
 * @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
 */
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@RestController
public class SpringbootdemosApplication {

    @GetMapping("/test/1002")
    public String test1() throws InterruptedException {
        Thread.sleep(1000);
        return "success";
    }

    /**
     * 测试Hystrix
     */
    @GetMapping("/test/1003")
    @HystrixCommand(groupKey = "server1", commandKey = "serverKey1",fallbackMethod = "fallback"
            , threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "30"), @HystrixProperty(name = "maxQueueSize", value = "100"), @HystrixProperty(name = "queueSizeRejectionThreshold", value = "20") }
            , commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //若干10s一个窗口内失败10次, 则达到触发熔断的最少请求量
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),//判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //断路10s后尝试执行, 默认为5s
    })
    public String test2() {
        return service();
    }

    private String service(){
        throw new RuntimeException("service 异常");
        //return "do service";
    }

    private String fallback(Throwable e) {
        e.printStackTrace();
        return "do fallback";
    }



    public static void main(String[] args) {
        System.out.println("正在启动。。。");
        new SpringApplicationBuilder(SpringbootdemosApplication.class)
                .run(args);
    }
}
