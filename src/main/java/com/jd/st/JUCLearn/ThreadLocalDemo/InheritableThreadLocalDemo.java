package com.jd.st.JUCLearn.ThreadLocalDemo;

import org.testng.collections.Maps;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhouZhiping
 * @date 2019/12/3 10:57
 * @Email zhouzhiping(a)jd.com
 */
public class InheritableThreadLocalDemo {


    public static void main(String[] args) {
        ThreadLocal<Map<String, String>> mainThreadLocal = new ThreadLocal<>();
        ThreadLocal<Map<String, String>> mainThreadLocal2 = new InheritableThreadLocal<>();
        Map mainThreadLocalMap = Maps.newHashMap();
        mainThreadLocalMap.put("myName","Michael");

        mainThreadLocal.set(mainThreadLocalMap);
        mainThreadLocal2.set(mainThreadLocalMap);

        new Thread( () -> {
            System.out.println(Thread.currentThread().getName() +"\t running, map is :" + mainThreadLocal.get());
        }).start();

        new Thread( () -> {
            System.out.println(Thread.currentThread().getName() +"\t running, map is :" + mainThreadLocal2.get());
        }).start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
