package com.example.demo.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by liuyumeng on 2018/11/21.
 */
public class LongEventFactory implements EventFactory {
    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
