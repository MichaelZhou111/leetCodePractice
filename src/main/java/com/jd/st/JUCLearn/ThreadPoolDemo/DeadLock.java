package com.jd.st.JUCLearn.ThreadPoolDemo;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhouZhiping
 * @date 2019/11/25 11:31
 * @Email zhouzhiping(a)jd.com
 */


class Resource implements Runnable {
    String lockA;
    String lockB;

    public Resource(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 本身拥有" + lockA + "\t 尝试获取" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 本身拥有" + lockB + "\t 尝试获取" + lockA);
            }
        }

    }
}

/**
 * @author zhouzhiping
 */
public class DeadLock {
    public static void main(String[] args) {
        Resource resourceA = new Resource("lockA", "lockB");
        Resource resourceB = new Resource("lockB", "lockA");
        new Thread(resourceA, "AAA").start();
        new Thread(resourceB, "BBB").start();

    }


}
