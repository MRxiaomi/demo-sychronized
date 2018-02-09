package com.example.demo;

import com.example.demo.springboot.core.AutoConfigurationDemo;
import com.example.demo.springboot.core.AutoConfigurationDemoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 若要使该类生效，需要注册到spring.factories
 *
 * 若将该实例打成jar包，其他项目进行引入，则会进行自动装配
 *
 */

@Configuration
@ComponentScan("com.example.demo")
@EnableConfigurationProperties(AutoConfigurationDemoProperties.class)
@ConditionalOnClass(AutoConfigurationDemo.class)//判断该类是否存在
@ConditionalOnProperty(prefix = "auto.configuration.demo",value = "enable",matchIfMissing = true)
public class AutoConfiguration {
    /**
     * 在HttpEncodingAutoConfiguration 已经帮我们自动配置
     * 配置http编码过滤器，对应原始的xml encodingFilter
     */

//    @Autowired
//    private HttpEncodingProperties httpEncodingProperties;
//    @Bean
//    @ConditionalOnMissingBean(CharacterEncodingFilter.class)
//    private CharacterEncodingFilter httpEncodingProperties(){
//        CharacterEncodingFilter characterEncodingFilter = new OrderedCharacterEncodingFilter();
//        characterEncodingFilter.setEncoding(this.httpEncodingProperties.getCharset().name());
//        characterEncodingFilter.setForceEncoding(this.httpEncodingProperties.isForce());
//        return characterEncodingFilter;
//    }

    /**
     * 当某个类存在时，自动配置该类的bean，并可将Bean的属性在application.properties中配置
     */
    @Autowired
    private AutoConfigurationDemoProperties autoConfigurationDemoProperties;

    @ConditionalOnMissingBean//容器中不存在该Bean时创建Bean
    @Bean
    public AutoConfigurationDemo autoConfigurationDemo(){
        AutoConfigurationDemo autoConfigurationDemo = new AutoConfigurationDemo();
        autoConfigurationDemo.setMsg(autoConfigurationDemoProperties.getMsg());
        return autoConfigurationDemo;
    }
}
