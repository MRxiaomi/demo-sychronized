package com.example.demo.disruptor;


/**
 * Created by liuyumeng on 2018/11/21.
 */
public class LongEventHandler implements com.lmax.disruptor.EventHandler<LongEvent> {
    private String name;

    public LongEventHandler(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(String.format("事件处理 %s %s", name, longEvent.getValue()));
    }
}
