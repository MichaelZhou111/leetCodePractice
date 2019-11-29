package com.jd.st.JUCLearn.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author ZhouZhiping
 * @date 2019/11/29 13:42
 * @Email zhouzhiping(a)jd.com
 *
 * https://juejin.im/post/5adbf8226fb9a07aac240a67
 */
class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t 运行call方法...");
        return "OK";
    }

}

public class SimpleDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 5, 300, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new ThreadFactoryBuilder().setDaemon(true).setNameFormat("Michael-t-%d").build(),
                new ThreadPoolExecutor.AbortPolicy());

        /**
         * 主线程阻塞demo
         */
       /* CompletableFuture<String> completableFuture = new CompletableFuture<>();
        System.out.println("主线程会被阻塞。。。");
        completableFuture.get();*/


        /**
         * simple future demo
         */
       /* MyCallable myCallable = new MyCallable();
        Future<String> future = executor.submit(myCallable);
        System.out.println(future.get());*/


        // Run a task specified by a Runnable Object asynchronously.
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                // Simulate a long-running Job
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                System.out.println("I'll run in a separate thread than the main thread.");
            }
        });
        // Block and wait for the future to complete
        future.get();
        System.out.println("end~");


        // Using Lambda Expression
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            // Simulate a long-running Job
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("I'll run in a separate thread than the main thread.");
        });

        //使用 supplyAsync() 运行一个异步任务并且返回结果 当任务不需要返回任何东西的时候，
        // CompletableFuture.runAsync() 非常有用。但是如果你的后台任务需要返回一些结果应该要怎么样？
        // Run a task specified by a Supplier object asynchronously
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Result of the asynchronous computation";
            }
        });
        // Block and get the result of the Future
        String result2 = future2.get();
        System.out.println(result2);


        // Using Lambda Expression
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        });
        // Block and get the result of the Future
        String result3 = future3.get();
        System.out.println(result3);

        /*一个关于Executor 和Thread Pool笔记
        你可能想知道，我们知道runAsync()和supplyAsync()方法在单独的线程中执行他们的任务。但是我们不会永远只创建一个线程。
        CompletableFuture可以从全局的 ForkJoinPool.commonPool()获得一个线程中执行这些任务。
        但是你也可以创建一个线程池并传给runAsync()和supplyAsync()方法来让他们从线程池中获取一个线程执行它们的任务。*/

        /**
         * CompletableFuture.get()方法是阻塞的。它会一直等到Future完成并且在完成后返回结果。
         但是，这是我们想要的吗？对于构建异步系统，我们应该附上一个回调给CompletableFuture，当Future完成的时候，自动的获取结果。
         如果我们不想等待结果返回，我们可以把需要等待Future完成执行的逻辑写入到回调函数中。
         作者：yekeqiang
         链接：https://juejin.im/post/5adbf8226fb9a07aac240a67
         来源：掘金
         */

        // Create a CompletableFuture
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        });

        // Attach a callback to the Future using thenApply()
        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
            return "Hello " + name;
        });

        // Block and get the result of the future.
        System.out.println(whatsYourNameFuture.get());
        System.out.println(greetingFuture.get()); // Hello Rajeev


        /**
         * 你也可以通过附加一系列的thenApply()在回调方法 在CompletableFuture写一个连续的转换。
         * 这样的话，结果中的一个 thenApply方法就会传递给该系列的另外一个 thenApply方法。
         */
        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        }).thenApply(name -> {
            return "Hello " + name;
        }).thenApply(greeting -> {
            return greeting + ", Welcome to the CalliCoder Blog";
        });

        System.out.println(welcomeText.get());


        /**
         *  thenAccept() 和 thenRun()
         如果你不想从你的回调函数中返回任何东西，仅仅想在Future完成后运行一些代码片段，你可以使用thenAccept()和 thenRun()方法，
         这些方法经常在调用链的最末端的最后一个回调函数中使用。
         CompletableFuture.thenAccept()持有一个Consumer<T>，返回一个CompletableFuture<Void>。
         */

        /**
         * 虽然thenAccept()可以访问CompletableFuture的结果，
         * 但thenRun()不能访Future的结果，它持有一个Runnable返回CompletableFuture：
         */

        Future<String > future4 =CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println( Thread.currentThread().getName()+"\t 运行...");
            return "Some Result";
        }).thenApply(result -> {
          /*
          Executed in the same thread where the supplyAsync() task is executed
          or in the main thread If the supplyAsync() task completes immediately (Remove sleep() call to verify)
        */
            System.out.println( Thread.currentThread().getName()+"\t 运行...");
            return "Processed Result";
        });
        System.out.println(future4.get());

        // thenApply 、thenApplyAsync
        Future<String > future5 =CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println( Thread.currentThread().getName()+"\t 运行...");
            return "Some Result123";
        }).thenApplyAsync(result -> {
            System.out.println( Thread.currentThread().getName()+"\t 运行...");
            return result+"\t Processed Result456";
        });
        System.out.println(future5.get());


    }
}
