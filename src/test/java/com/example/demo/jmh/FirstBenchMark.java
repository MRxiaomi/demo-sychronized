package com.example.demo.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by liuyumeng on 2018/6/21.
 */
@State(Scope.Thread)// 每个测试线程一个实例
@BenchmarkMode(Mode.AverageTime)  // 测试方法平均执行时间
@OutputTimeUnit(TimeUnit.MICROSECONDS )// 输出结果的时间粒度为微秒
//@Threads(100)
@Timeout(time = 100, timeUnit = TimeUnit.MILLISECONDS)
public class FirstBenchMark {
    private static org.slf4j.Logger log = LoggerFactory.getLogger(FirstBenchMark.class);
    @Benchmark
    public void add(){
        String a = "a";
        String b = "b";
        String c = "c";
        String s = a + b + c;
        log.error(s);
    }


    public static void main(String[] args) throws RunnerException {
        // 使用一个单独进程执行测试，执行5遍warmup，然后执行5遍测试
        org.openjdk.jmh.runner.options.Options opt = new OptionsBuilder().include(FirstBenchMark.class.getSimpleName()).forks(1).warmupIterations(5)
                .measurementIterations(5).build();
        new Runner(opt).run();
    }
}
