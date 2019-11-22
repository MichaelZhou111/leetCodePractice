package com.jd.st.demo.guavaEventBus;

import org.apache.commons.lang3.time.StopWatch;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhouZhiping
 * @date 2019/9/5 11:12
 * @Email zhouzhiping(a)jd.com
 */
public class DataObserver1 {

    /**
     * 只有通过@Subscribe注解的方法才会被注册进EventBus
     * 而且方法有且只能有1个参数
     *
     * @param msg
     */
    @Subscribe
    public void func(String msg) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("begin=="+stopWatch.getTime(TimeUnit.SECONDS));
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end=="+stopWatch.getTime(TimeUnit.SECONDS));
        stopWatch.stop();
    }
}
