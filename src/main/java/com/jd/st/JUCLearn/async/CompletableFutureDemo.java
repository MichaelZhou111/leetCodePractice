package com.jd.st.JUCLearn.async;

import java.util.concurrent.CompletableFuture;

/**
 * @author ZhouZhiping
 * @date 2019/11/28 15:57
 * @Email zhouzhiping(a)jd.com
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        String result = CompletableFuture.supplyAsync(()->{return "Hello ";}).thenApplyAsync(v -> v + "world").join();
        System.out.println(result);

    }
}
