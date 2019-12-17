package com.jd.st.demo.guavaBasic;

import com.google.common.base.Preconditions;

/**
 * @author ZhouZhiping
 * @date 2019/12/17 10:08
 * @Email zhouzhiping(a)jd.com
 */
public class PreconditionsDemo {
    public static void main(String[] args) {
        Preconditions.checkArgument(1 > 2,"own error message");
        System.out.println("not here");

    }

}
