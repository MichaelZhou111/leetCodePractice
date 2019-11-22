package com.jd.st.leetCode.string;

/**
 * @author ZhouZhiping
 * @date 2019/5/24 16:49
 * @Email zhouzhiping(a)jd.com
 * https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/879/
 */
public class ReverseString {

    public static void main(String args[]){
        String input = "hello Reverse";
        char[] inputCharArray = input.toCharArray();
        System.out.println(reverseString(inputCharArray));

    }
    /**
     * 字符串交换位置算法
     * @param s
     * @return
     */

    public static char[] reverseString(char[] s) {
        if (null != s) {
            int length = s.length;
            for (int i = 0; i < length / 2; i++) {
                char temp = s[i];

                s[i] = s[length - i - 1];
                s[length - i - 1] = temp;
            }
        } else {
            System.out.println("the input must not be null");
        }
        return s;
    }
}
