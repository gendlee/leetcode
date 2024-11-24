package utils;

import common.linkedlist.ListNode;


public class Printer {
    public static void printLinkedList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.next;
        }
        System.out.print("null");
        System.out.println();
    }

    public static void printIntArray(int[] nums) {
        for (int val : nums) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
