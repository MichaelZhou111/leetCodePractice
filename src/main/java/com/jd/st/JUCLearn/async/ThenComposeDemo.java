package com.jd.st.JUCLearn.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author ZhouZhiping
 * @date 2019/11/29 13:42
 * @Email zhouzhiping(a)jd.com
 *
 * https://juejin.im/post/5adbf8226fb9a07aac240a67
 */
@Data
@AllArgsConstructor
class User{
    private String name;
    private String id;
}

public class ThenComposeDemo {

    CompletableFuture<User> getUsersDetail(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            //UserService.getUserDetails(userId); remote invoke
            return new User("Michael","33");
        });
    }

    CompletableFuture<Double> getCreditRating(User user) {
        return CompletableFuture.supplyAsync(() -> {
            //CreditRatingService.getCreditRating(user);  remote invoke
            return 100.0;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 5, 300, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new ThreadFactoryBuilder().setDaemon(true).setNameFormat("Michael-t-%d").build(),
                new ThreadPoolExecutor.AbortPolicy());

        //then compose demo-->before
        ThenComposeDemo demo = new ThenComposeDemo();
        CompletableFuture<CompletableFuture<Double>> result = demo.getUsersDetail("123").thenApply(user-> demo.getCreditRating(user));
        System.out.println(result.get().get());

        // then compose demo -> after

        /**
         * 规则就是-如果你的回调函数返回一个CompletableFuture，但是你想从CompletableFuture链中获取一个直接合并后的结果，这时候你可以使用thenCompose()
         */
        CompletableFuture<Double> result2= demo.getUsersDetail("123").thenCompose( user ->demo.getCreditRating(user));
        System.out.println(result2.get());

        System.out.println("===========================");


        /**
         * 使用thenCombine()组合两个独立的 future
         虽然thenCompose()被用于当一个future依赖另外一个future的时候用来组合两个future。
         thenCombine()被用来当两个独立的Future都完成的时候，用来做一些事情。
         */
        System.out.println("Retrieving weight.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });

        System.out.println("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 177.8;
        });

        System.out.println("Calculating BMI.");
        CompletableFuture<Double> combinedFuture = weightInKgFuture
                .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
                    Double heightInMeter = heightInCm/100;
                    return weightInKg/(heightInMeter*heightInMeter);
                });

        System.out.println("Your BMI is - " + combinedFuture.get());
    }
}
