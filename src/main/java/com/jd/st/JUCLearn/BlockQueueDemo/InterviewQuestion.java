package com.jd.st.JUCLearn.BlockQueueDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author ZhouZhiping
 * @date 2019/11/22 11:22
 * @Email zhouzhiping(a)jd.com
 *
 *  题目： 一个整形的初始值为0，2个线程交替去操作，一个加一，一个减一，进行5遍
 */
public class InterviewQuestion {

    class Resource {
        private int i = 0;
         Lock lock = new ReentrantLock();
        public void increacement() {
            System.out.println("increase i" +  (i++));
        }

        public void decreacement() {
            System.out.println("decrease i "+(i--));
        }

    }

        public static void main(String[] args){
            InterviewQuestion interviewQuestion = new InterviewQuestion();
            interviewQuestion.lockWay();
        }

        void lockWay(){
            Resource resource = new Resource();

                new Thread( () -> {
                    System.out.println(Thread.currentThread().getName() + "\t is running...");
                    IntStream.range(0, 5).forEach((x) -> {
                        System.out.println(Thread.currentThread().getName() + "\t is running..."+x);
                        try {
                            if (resource.lock.tryLock()) {
                                resource.increacement();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            resource.lock.unlock();
                        }
                    });
                },"AAA").start();

            new Thread( () -> {
                System.out.println(Thread.currentThread().getName() + "\t is running...");
                IntStream.range(0, 5).forEach((x) -> {
                    System.out.println(Thread.currentThread().getName() + "\t is running..."+x);
                    try {
                        if (resource.lock.tryLock()) {
                            resource.decreacement();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        resource.lock.unlock();
                    }
                });
            },"BBB").start();


        }


}
