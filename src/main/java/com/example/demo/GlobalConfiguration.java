package com.example.demo;

import com.example.demo.configuration.servcie.ConfigurationTestService;
import com.example.demo.configuration.useservice.UseConfigurationTestService;
import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.apache.catalina.valves.AccessLogValve;
import org.apache.catalina.valves.Constants;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * Created by liuyumeng on 2018/2/9.
 * <p>
 * 本项目使用java配置和注解共同使用,java配置是使用Configuration+Bean,@Bean放在方法上，声明当前方法返回一个Bean
 * 2.
 */
@AutoConfigureOrder(-2147483648)
@Configuration
@ComponentScan("com.example.demo")
public class GlobalConfiguration {
    //ConfigurationTestService-使用功能类的Bean
    @Bean
    public ConfigurationTestService configurationTestService() {
        return new ConfigurationTestService();
    }

    @Bean
    public UseConfigurationTestService useConfigurationTestService() {
        UseConfigurationTestService useConfigurationTestService = new UseConfigurationTestService();
        //依赖注入
        useConfigurationTestService.setConfigurationTestService(configurationTestService());
        return useConfigurationTestService;
    }

    @Bean
    //@ConditionalOnMissingBean(value = EmbeddedServletContainerFactory.class)
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        //设置端口
        factory.setPort(9092);
        //设置404错误界面
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
        //设置在容器初始化的时候触发
        factory.addInitializers((servletContext) -> {
            System.out.println(" = = = = 获取服务器信息 = = " + servletContext.getServerInfo());
        });
        //设置最大连接数和最大线程数
        factory.addConnectorCustomizers((connector) -> {
            Http11NioProtocol protocolHandler = (Http11NioProtocol) connector.getProtocolHandler();
            protocolHandler.setMaxConnections(100);
            protocolHandler.setMaxThreads(50);
            protocolHandler.setAcceptCount(100);
        });
        //设置访问日志记录文件的目录
        factory.addContextValves(getLogAccessLogValue());
        return factory;
    }

    private AccessLogValve getLogAccessLogValue() {
        AccessLogValve accessLogValve = new AccessLogValve();
        accessLogValve.setDirectory("d:/tmp/logs");
        accessLogValve.setEnabled(true);
        accessLogValve.setPattern(Constants.AccessLog.COMMON_PATTERN);
        accessLogValve.setPrefix("SpringBoot-Access-Log");
        accessLogValve.setSuffix(".txt");
        return accessLogValve;
    }


    //声明一个HystrixCommandAspect代理类，用于以后被Hystrix相关注解的切面
    @Bean
    public HystrixCommandAspect hystrixAspect() {
        return new HystrixCommandAspect();
    }

    //用于监控Hystrix统计信息
    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        registration.addUrlMappings("/hystrix.stream");
        return registration;
    }
}
