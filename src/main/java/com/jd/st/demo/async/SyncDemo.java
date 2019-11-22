package com.jd.st.demo.async;

/**
 * @author ZhouZhiping
 * @date 2019/8/20 12:16
 * @Email zhouzhiping(a)jd.com
 */
public class SyncDemo {
    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        int i = syncCalculate();
        System.out.println("计算结果:" + i);
        System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + " ms");
    }

    //最常用的同步调用
    static int syncCalculate() {
        System.out.println("执行耗时操作...");
        timeConsumingOperation();
        return 100;
    }

    static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
