package com.jd.st.demo.guavaEventBus;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * @author ZhouZhiping
 * @date 2019/9/5 11:24
 * @Email zhouzhiping(a)jd.com
 */
public class EventBustTestMain {

    public static void main(String[] args) {
        DataObserver1 observer1 = new DataObserver1();
        DataObserver2 observer2 = new DataObserver2();

        EventBusCenter.register(observer1);
        EventBusCenter.register(observer2);

        System.out.println("============   start  ====================");

        // 只有注册的参数类型为String的方法会被调用
        //EventBusCenter.post("post string method1");
        //EventBusCenter.post("post string method2");
        //EventBusCenter.post("post string method3");

        /**
         * async
         */
        IntStream.range(1,20).forEach(  (i)->  {
            System.out.println("========="+i);
            EventBusCenter.post(i);
        });


        System.out.println("main thread is running");


     /*   System.out.println("============ after unregister ============");
        // 注销observer2
        EventBusCenter.unregister(observer2);
        EventBusCenter.post("post string method");
        EventBusCenter.post(123);

        System.out.println("============    end           =============");*/
    }
}
