package com.jd.st.demo.guavaEventBus;

import com.google.common.eventbus.EventBus;

/**
 * @author ZhouZhiping
 * @date 2019/9/5 11:09
 * @Email zhouzhiping(a)jd.com
 */
public class EventBusCenter {

    private static EventBus eventBus = new EventBus();

    private EventBusCenter() {

    }

    public static EventBus getInstance() {
        return eventBus;
    }

    public static void register(Object obj) {
        eventBus.register(obj);
    }

    public static void unregister(Object obj) {
        eventBus.unregister(obj);
    }

    public static void post(Object obj) {
        eventBus.post(obj);
    }
}
