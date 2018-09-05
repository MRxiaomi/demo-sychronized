package com.example.demo.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by liuyumeng on 2018/8/15.
 */
public class JdkProxyDemo {
    public static void main(String[] args) {
        //被代理的target对象
        Subject subject = new SubjectImpl();

        //生成代理对象
        Subject proxyInstance =(Subject) Proxy.newProxyInstance(JdkProxyDemo.class.getClassLoader(), new Class[]{Subject.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("jdk proxy before ....");
                //对target对象的方法进行代理
                method.invoke(subject,args);
                System.out.println("jdk proxy after ....");
                return proxy;
            }
        });

        proxyInstance.request();
    }
}
