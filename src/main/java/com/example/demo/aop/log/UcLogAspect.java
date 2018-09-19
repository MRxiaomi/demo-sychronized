package com.example.demo.aop.log;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
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
public class UcLogAspect {
    public static final String dateformat = "yyyy:MM:dd HH:mm:ss";

    public static final org.slf4j.Logger logger =  LoggerFactory.getLogger(UcLogAspect.class);

    //1.声明Pointcut-注解的方式 凡是包含该注解的方法，组合为切面
    @Pointcut("@annotation(com.example.demo.aop.log.Log)")
    public void annotationPointCut(){}

    //3.建言
    @Around("annotationPointCut()")
    public Object after(ProceedingJoinPoint joinPoint){
        try {
            //获取方法签名
            MethodSignature signature= (MethodSignature) joinPoint.getSignature();
            //java reflect相关类，通过反射得到注解
            Method method = signature.getMethod();
            method.setAccessible(true);
            Log log = method.getAnnotation(Log.class);

            if (log.ignore()) {
                //proceed() 方法执行切面方法，并返回方法返回值
                return joinPoint.proceed();
            }

            //获取类名和方法名
            Class<?> targetClass = method.getDeclaringClass();
            String target = targetClass.getName() + "#" + method.getName();

            String params = JSONObject.toJSONStringWithDateFormat(joinPoint.getArgs(),dateformat, SerializerFeature.WriteMapNullValue);

            logger.info("方法调用开始 --> {} 参数:{}", target, params);

            long start = System.currentTimeMillis();
            //如果类名上和方法上都没有Log注解，则直接执行 proceed()
            Object result = joinPoint.proceed();
            long timeConsuming = System.currentTimeMillis() - start;

            logger.info("方法调用结束 <-- {} 返回值:{} 耗时:{}ms", target, JSONObject.toJSONStringWithDateFormat(result, dateformat, SerializerFeature.WriteMapNullValue), timeConsuming);

            return result;
        }catch (Throwable e){
            e.printStackTrace();
        }
        return null;
    }
}
