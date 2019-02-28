package com.example.demo.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by liuyumeng on 2018/11/21.
 */
public class LongEventMain {
    public static void main(String[] args) throws InterruptedException {
        //为消费者构造新的线程
        Executor executor = Executors.newCachedThreadPool();
        //事件工厂
        EventFactory<LongEvent> factory = new LongEventFactory();
        //Specify the size of the ring buffer, must be power of 2
        int bufferSize = 1024;
        //构造disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());

        disruptor.handleEventsWith(new LongEventHandler("1"),new LongEventHandler("2"));

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0,l);
            producer.onData(bb);
            Thread.sleep(1000);
        }
    }
}
