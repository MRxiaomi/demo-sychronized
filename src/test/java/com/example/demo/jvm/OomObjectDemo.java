package com.example.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyumeng on 2018/1/31.
 * 以64K／100毫秒的速度往ava堆填数据，一共1000次，使用jconsole的内存页进行监控
 *
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class OomObjectDemo {
    static class OomObject {
        private byte[] placeholder = new byte[64 * 1024];//todo 以长度分配空间的底层原理
    }

    private static void fillHeap(int num) throws InterruptedException {
        Thread.sleep(30000);

        List<OomObject> oomObjects = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            //Thread.sleep(50);
            oomObjects.add(new OomObject());
            System.out.println("------" + i);
        }
    }

    public static void main(String[] args) throws Exception {
        fillHeap(10000);
        //System.gc(); 为什么gc没有立即生效？
        System.out.println("------结束" );
    }
}
