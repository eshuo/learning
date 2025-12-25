package com.wyci;

/**
 * @Description
 * @Author wangshuo
 * @Date 2025-08-13 10:15
 * @Version V1.0
 */
public class L2 {


    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode root = new ListNode(0);
        ListNode sumNode = root;
        //一直从最后一位开始加
        int carry = 0;

        while (null != l1 || null != l2) {

            int val1 = null == l1 ? 0 : l1.val;
            int val2 = null == l2 ? 0 : l2.val;

            int num = val1 + val2 + carry;

            carry = num / 10;

            sumNode.next = new ListNode(num % 10);
            sumNode = sumNode.next;
            if (null != l1) {
                l1 = l1.next;
            }
            if (null != l2) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            sumNode.next = new ListNode(carry);
        }

        //判断长度是否相同

        return root.next;
    }

    public class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
