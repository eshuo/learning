package com.wyci;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description leetcode215
 * @Author wangshuo
 * @Date 2024-05-31 14:13
 * @Version V1.0
 */
public class Demo215 {

//    给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
//
//请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
//
//你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
//
//示例 1:
//
//输入: [3,2,1,5,6,4], k = 2
//输出: 5
//示例 2:
//
//输入: [3,2,3,1,2,4,5,5,6], k = 4
//输出: 4


    public static void main(String[] args) {

        //思路： 反序排序后获取对应元素
//        System.out.println(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    public static int findKthLargest(int[] nums, int k) {
        if (null == nums) {
            return -1;
        }
        List<Integer> numsList = new ArrayList<>(nums.length);
        for (int num : nums) {
            numsList.add(num);
        }
        numsList.sort(Integer::compareTo);
        Collections.reverse(numsList);
        numsList.forEach(System.err::println);
        return numsList.get(k - 1);
    }

}
