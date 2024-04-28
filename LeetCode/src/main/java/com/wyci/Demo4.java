package com.wyci;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author wangshuo
 * @Date 2024-04-22 11:06
 * @Version V1.0
 */
public class Demo4 {

    /**
     * 中位数计算方式： 1. 奇数个数，直接取中间的数 2. 偶数个数，取中间两个数的平均值 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * <p>
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums1 = [1,3], nums2 = [2] 输出：2.00000 解释：合并数组 = [1,2,3] ，中位数 2 示例 2：
     * <p>
     * 输入：nums1 = [1,2], nums2 = [3,4] 输出：2.50000 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5 提示：
     * <p>
     * nums1.length == m nums2.length == n 0 <= m <= 1000 0 <= n <= 1000 1 <= m + n <= 2000 -106 <= nums1[i], nums2[i] <= 106 Related Topics 数组 | 二分查找 | 分治
     */


    public static void main(String[] args) {

//     * 输入：nums1 = [1,3], nums2 = [2]
//     * 输出：2.00000
//            * 解释：合并数组 = [1,2,3] ，中位数 2

//        int[] num1 = new int[]{1, 3};
//        int[] num2 = new int[]{2};
//
//        System.out.println(findMedianSortedArrays(num1, num2));

//     * 输入：nums1 = [1,2], nums2 = [3,4]
//     * 输出：2.50000
//        解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

        //测试结果:-1.00000  期望结果:0.0
        int[] num3 = new int[]{-1, 0, 0, 0, 0, 0, 1};
        int[] num4 = new int[]{0, 0, 0, 0, 0};

        System.out.println(findMedianSortedArrays(num3, num4));

        num3 = new int[]{1, 3, 4, 9};
        num4 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println(findMedianSortedArrays(num3, num4));


    }

    /**
     * 奇偶中位数
     */

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //取数组并集
        // 将数组转换为set集合
        final List<Integer> collect = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        collect.addAll(Arrays.stream(nums2).boxed().collect(Collectors.toList()));
        collect.sort(Integer::compareTo);
        final Integer[] array = collect.toArray(new Integer[0]);
        if (array.length % 2 == 0) {
            //偶数
            return (double) ((array[array.length / 2 - 1] + array[array.length / 2])) / 2;
        } else {
            //奇数
            return array[array.length / 2];
        }
    }


}
