package com.example.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuyumeng
 */
public class ConditionTest {
    private static Lock lock = new ReentrantLock();
    private static AtomicInteger atomicInteger = new AtomicInteger();

    private static class TreadTest implements Runnable {
        private Condition condition;
        private Condition nextCondition;

        public TreadTest(Condition condition, Condition nextCondition) {
            this.condition = condition;
            this.nextCondition = nextCondition;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    System.out.println(atomicInteger.addAndGet(1));
                    nextCondition.signal();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();

        Thread threadA = new Thread(new TreadTest(conditionA,conditionB));
        Thread threadB = new Thread(new TreadTest(conditionB,conditionC));
        Thread threadC = new Thread(new TreadTest(conditionC,conditionA));

        threadA.start();
        threadB.start();
        threadC.start();
    }

}
