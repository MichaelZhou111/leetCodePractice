package com.jd.st.demo.guavaEventBus;

import com.google.common.eventbus.Subscribe;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author ZhouZhiping
 * @date 2019/9/5 11:13
 * @Email zhouzhiping(a)jd.com
 */
public class DataObserver2 {

    ExecutorService executor = Executors.newFixedThreadPool(2);
    //ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(3, new BasicThreadFactory.Builder().namingPattern("upload-file-schedule-pool-%d").daemon(true).build());

    /**
     * post() 不支持自动装箱功能,只能使用Integer,不能使用int,否则handlersByType的Class会是int而不是Intege
     * 而传入的int msg参数在post(int msg)的时候会被包装成Integer,导致无法匹配到
     */
    @Subscribe
    public void func(Integer msg) {
        System.out.println("Integer msg: " + msg);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println( msg +" task started!" );
                try {
                    //模拟耗时操作
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return  msg +" task finished!";
            }
        }, executor);

        //采用lambada的实现方式
        future.thenAccept(e -> System.out.println(e + " ok"));

    }
}
