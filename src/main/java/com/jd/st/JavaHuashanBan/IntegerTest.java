package com.jd.st.JavaHuashanBan;

import java.util.Arrays;
import java.util.List;

/**
 * @author ZhouZhiping
 * @date 2019/6/27 9:20
 * @Email zhouzhiping(a)jd.com
 */
public class IntegerTest {

    public static void main(String[] args){
        Integer a = Integer.MAX_VALUE;
        //2,147,483,647
        System.out.println( a);

        /**
         * another
         */
        String str = "a,b,c,,";
        String[] ary = str.split(",");
        // 预期大于3，结果是3
        System.out.println(ary.length);
        for (String s : ary) {
            System.out.println(s);
        }
        List<String> stringList = Arrays.asList(ary);
        stringList.stream().forEach(System.out::println);
        stringList.stream().forEach( x -> System.out.println(x));
    }
}
