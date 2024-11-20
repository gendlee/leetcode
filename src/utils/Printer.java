package utils;

import linkedlist.ListNode;


public class Printer {
    public static void printLinkedList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.getVal() + "->");
            cur = cur.getNext();
        }
        System.out.print("null");
        System.out.println();
    }
}
