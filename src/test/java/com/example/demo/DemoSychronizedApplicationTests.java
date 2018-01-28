package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.attribute.IntegerSyntax;
import java.util.Vector;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSychronizedApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Test
    public void testSychronized() {
        System.out.println("--------test");
    }

    /**
     * 对vector线程安全的测试
     */

    public static Vector<Integer> vector = new Vector<>();

    @Test
    public void test() {
        while (Thread.activeCount() < 20) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            Thread thread1 = new Thread(() -> {
                synchronized (vector) {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            });

            Thread thread2 = new Thread(() -> {
                synchronized (vector) {
                    for (int i = 0; i < vector.size(); i++) {
                        System.out.println(vector.get(i));
                    }
                }
            });

            thread1.start();
            thread2.start();
        }
    }

    @Test
    public void demo1() {
        String s = "s";
        synchronized (s) {
            doSomething();
        }
    }

    private void doSomething(){}
}
