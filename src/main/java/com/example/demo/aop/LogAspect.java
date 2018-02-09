package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by liuyumeng on 2018/2/9.
 *
 * Aspect： Aspect 声明类似于 Java 中的类声明，在 Aspect 中会包含着一些 Pointcut 以及相应的 Advice。
 * Joint point：表示在程序中明确定义的点，典型的包括方法调用，对类成员的访问以及异常处理程序块的执行等等，它自身还可以嵌套其它 joint point。
 * Pointcut：表示一组 joint point，这些 joint point 或是通过逻辑关系组合起来，或是通过通配、正则表达式等方式集中起来，它定义了相应的 Advice 将要发生的地方。
 * Advice：Advice 定义了在 pointcut 里面定义的程序点具体要做的操作，它通过 before、after 和 around 来区别是在每个 joint point 之前、之后还是代替执行的代码。
 */
@Aspect
@Component
public class LogAspect {
    //1.声明Pointcut-注解的方式 凡是包含该注解的方法，组合为切面
    @Pointcut("@annotation(com.example.demo.aop.Action)")
    public void annotationPointCut(){}

    //2.声明Pointcut-方法的 凡是符合规则的方法，组合为切面
    @Pointcut("execution(* com.example.demo.aop.AopMethodService.*(..))")
    public void methodPointCut(){}

    //3.建言
    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint){
        MethodSignature signature= (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        method.setAccessible(true);
        Action action = method.getAnnotation(Action.class);
        System.out.println(action.name());
    }

    //4.建言
    @Before("methodPointCut()")
    public void before(JoinPoint joinPoint){
        MethodSignature signature= (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println(method.getName());
    }

}
