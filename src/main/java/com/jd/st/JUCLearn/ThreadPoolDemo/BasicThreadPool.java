package com.jd.st.JUCLearn.ThreadPoolDemo;

import com.google.common.primitives.Ints;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author ZhouZhiping
 * @date 2019/11/25 11:31
 * @Email zhouzhiping(a)jd.com
 */
public class BasicThreadPool {


    public static void main(String[] args) {
        //一个池子，5个线程
        //ExecutorService executorService = Executors.newFixedThreadPool(5);

        //一个池子，1个线程
        //ExecutorService executorService = Executors.newSingleThreadExecutor();

        //一个池子，N个线程
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            //模拟10个用户办理业务，每个用户就是来自一个外部的请求线程
            IntStream.range(0, 100).forEach((i) -> {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() +"\t 办理业务...");
                });
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }


}
