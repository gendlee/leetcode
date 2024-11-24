package utils;

import common.linkedlist.ListNode;

import java.util.Arrays;

public class CompareUtil {

    /**
     * 比较数组内容是否相同，忽略索引顺序
     * @param a
     * @param b
     * @return true/false
     */
    public static boolean arraysEqualIgnoreOrder(int[] a, int[] b) {
        if (a == null || b == null) {
            return false;
        }
        int[] sortedA = a.clone();
        int[] sortedB = b.clone();
        Arrays.sort(sortedA);
        Arrays.sort(sortedB);

        return Arrays.equals(sortedA, sortedB);
    }

    public static boolean linkedListEqualArraysValue(ListNode head, int[] values) {
        ListNode cur = head;
        for (int val : values) {
            if (cur == null || cur.val != val) {
                return false;
            }
            cur = cur.next;
        }

        // 长度也相等时返回true
        return cur == null;
    }
}
