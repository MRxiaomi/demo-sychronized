package com.example.demo.aop;

import java.lang.annotation.*;

/**
 * Created by liuyumeng on 2018/2/9.
 *
 * 1.Documented 注解表明这个注解应该被 javadoc工具记录. 默认情况下,javadoc是不包括注解的. 但如果声明注解时指定了 @Documented,
 * 则它会被 javadoc 之类的工具处理, 所以注解类型信息也会被包括在生成的文档中.
 *
 * 2.指明该类型的注解可以注解的程序元素的范围。该元注解的取值可以为TYPE,METHOD,CONSTRUCTOR,FIELD等。如果Target元注解没有出现，那么定义的注解可以应用于程序的任何元素。
 *
 * 3.指明了该Annotation被保留的时间长短。RetentionPolicy取值为SOURCE,CLASS,RUNTIME。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface Action {
    String name();
}
