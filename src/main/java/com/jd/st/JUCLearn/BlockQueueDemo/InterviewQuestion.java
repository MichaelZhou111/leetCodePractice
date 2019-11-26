package com.jd.st.JUCLearn.BlockQueueDemo;

import java.util.concurrent.atomic.AtomicInteger;
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
        volatile private AtomicInteger i = new AtomicInteger(0);
         Lock lock = new ReentrantLock();
        public void increase() {
            try {
                while (!lock.tryLock()) {
                }
                System.out.println(Thread.currentThread().getName()+"\t 当前值是:"+ i.incrementAndGet());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void descrease() {
            try {
                while (!lock.tryLock()) {
                }
                System.out.println(Thread.currentThread().getName()+"\t 当前值是:"+ i.decrementAndGet());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
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
                        resource.increase();
                    });
                },"AAA").start();

            new Thread( () -> {
                System.out.println(Thread.currentThread().getName() + "\t is running...");
                IntStream.range(0, 5).forEach((x) -> {
                    resource.descrease();
                });
            },"BBB").start();


        }


}
