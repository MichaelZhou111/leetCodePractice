package com.jd.st.leetCode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouZhiping
 * @date 2019/5/24 10:46
 * @Email zhouzhiping(a)jd.com
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String args[]) {
        int[] array = new int[]{1, 1, 2};
        //System.out.println(removeDuplicatesWithExtraList(array));
        System.out.println(removeDuplicates(array));

    }

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else {
            int theRightPlace = 1;
            for (int i = 0; i < nums.length - 1; i++) {
                if(nums[i] == nums[i+1]){
                    // do nothing, go into next iteration
                }else {
                    nums[theRightPlace] = nums[i+1];
                    theRightPlace++;
                }
            }
            return theRightPlace;
        }
    }

    /**
     * this is not allowed by leetCode
     * because extra space is used by a list  , leetCode asks a "in-place algorithm"
     *
     * @param nums
     * @return
     */
    public static String removeDuplicatesWithExtraList(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return "0";
        } else {
            result.add(nums[0]);
            for (int i = 0; i < nums.length; i++) {
                if (result.get(result.size() - 1) != nums[i]) {
                    result.add(nums[i]);
                }
            }
            return result.toString();
        }
    }

}

