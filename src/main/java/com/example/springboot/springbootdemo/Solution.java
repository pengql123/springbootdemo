package com.example.springboot.springbootdemo;


public class Solution {
    public static void main(String[] args) {
        ListNode listNode = new ListNode();
        listNode.val = 1;
        System.out.println(isPail(listNode));


    }
    public static boolean isPail (ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode reverseList = reverse(slow);
        ListNode cur1 = reverseList, cur2 = head;
        while (cur1 != null) {
            if (cur1.val != cur2.val) {
                return false;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return true;
    }

    public static ListNode reverse(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}

 class ListNode {
    int val;
   ListNode next = null;
 }
