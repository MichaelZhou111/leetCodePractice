package com.jd.st.demo.async;

import java.util.concurrent.*;

/**
 * @author ZhouZhiping
 * @date 2019/8/20 12:16
 * @Email zhouzhiping(a)jd.com
 */
public class FutureDemo1 {
    /**
     * 上述的 syncCalculate 方法是一个耗时的操作，为了优化性能，我们可以考虑使用 Future 模式。
     * Future 模式相当于一个占位符，代表一个操作的未来的结果，其简单的概念不在本文中介绍，直接给出总结：Future 模式可以细分为将来式和回调式两种模式。
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long l = System.currentTimeMillis();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("执行耗时操作...");
                timeConsumingOperation();
                return 100;
            }
        }); //<1>
        //其他耗时操作..<3>
        System.out.println("计算结果:" + future.get());//<2>
        System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + " ms");
    }

    static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
