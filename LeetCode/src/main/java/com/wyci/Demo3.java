package com.wyci;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author wangshuo
 * @Date 2024-04-10 15:42
 * @Version V1.0
 */
public class Demo3 {

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "abcabcbb" 输出: 3 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。 示例 2:
     * <p>
     * 输入: s = "bbbbb" 输出: 1 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。 示例 3:
     * <p>
     * 输入: s = "pwwkew" 输出: 3 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring(" "));//1
        System.out.println(lengthOfLongestSubstring("abcabcbb"));//3
        System.out.println(lengthOfLongestSubstring("bbbbb"));//1
        System.out.println(lengthOfLongestSubstring("pwwkew"));//3
        System.out.println(lengthOfLongestSubstring("dvdf"));//3
    }


    /**
     * 每次遍历后有重复的进行截取字符串到重复的字符位置
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }
        int maxLength = 0;
        StringBuilder resStr = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String indexStr = String.valueOf(s.charAt(i));
            if (resStr.indexOf(indexStr) >= 0) {
                //存在就移动一位下标  indesStr = a  resStr=> abc -> bc
                resStr.delete(0, resStr.indexOf(indexStr) + 1);
            }
            resStr.append(indexStr);
            final int length = resStr.length();
            if (maxLength < length) {
                maxLength = length;
            }
        }
        return maxLength;
    }


    /* 滑动窗口算法框架 */
    public static int slidingWindow(String s) {
        Map<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;
        int res = 0; // 记录结果
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            window.put(c, window.getOrDefault(c, 0) + 1);
            // 判断左侧窗口是否要收缩
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                // 进行窗口内数据的一系列更新
                window.put(d, window.get(d) - 1);
            }
            // 在这里更新答案
            res = Math.max(res, right - left);
        }
        return res;
    }
}
