package com.jd.st.JUCLearn.ThreadPoolDemo;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author ZhouZhiping
 * @date 2019/11/25 11:31
 * @Email zhouzhiping(a)jd.com
 */
public class ThreadPoolInRealProject {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                5,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            for (int i = 0; i < 20; i++) {
                threadPoolExecutor.execute(() ->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务..." );
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }


}
