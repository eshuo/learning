package com.wyci;

/**
 * @Description 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * @Author wangshuo
 * @Date 2025-07-24 10:19
 * @Version V1.0
 */
public class Lcode42 {


    public static void main(String[] args) {
        int[] num = new int[]{4,2,0,3,2,5,2,3,5,6,7,1,2};
        System.out.println(trap(num));
    }


    /**
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1] 输出：6
     */
    public static int trap(int[] height) {
        int leftMax = 0;
        int result = 0;
        for (int i = 0; i < height.length - 1; i++) {
            int num = height[i];
            if (leftMax <= num) {
                leftMax = num;
                continue;
            }
            int rightMax = 0;
            //取右边最大
            for (int i1 = i + 1; i1 < height.length; i1++) {
                int i2 = height[i1];
                if (rightMax < i2 && i2 > num) {
                    rightMax = i2;
                }
            }
            if (rightMax > 0) {
                result += Math.min(leftMax, rightMax) - num;
            }
        }
        return result;
    }

    @Deprecated
    public static int trap2(int[] height) {
        int leftMax = 0;

        int result = 0;

        for (int i = 0; i < height.length - 1; i++) {
            leftMax = Math.max(leftMax, height[i]);

            int num = height[i];
            leftMax = Math.max(leftMax, num);
            int rightMax = 0;
            //取右边最大
            for (int i1 = i + 1; i1 < height.length; i1++) {
                int i2 = height[i1];
                if ( i2 > num) {
                    rightMax = Math.max(rightMax,i2);
                }
            }
            if (rightMax > 0) {
                result += Math.min(leftMax, rightMax) - num;
            }
        }
        return result;
    }


}
