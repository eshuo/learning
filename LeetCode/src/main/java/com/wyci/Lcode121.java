package com.wyci;

/**
 * @Description
 * @Author wangshuo
 * @Date 2025-07-24 15:57
 * @Version V1.0
 */
public class Lcode121 {


    public static void main(String[] args) {


        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
    }


    /**
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * @param prices
     * @return
     */
    public static  int maxProfit(int[] prices) {

        //低买高卖

        //最低成本
        int min = Integer.MAX_VALUE;
        //最高价格卖
        int max = 0;

        for (int price : prices) {
            min = Math.min(min,price);
            //利润最大化
            max =  Math.max(price-min,max);
        }

        return max;
    }

}
