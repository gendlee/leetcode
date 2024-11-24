package common.linkedlist;

public class LinkedList {
    public ListNode head;

    /**
     * 指定数字范围创建链表
     * @param valStart 起始值（含）
     * @param valEnd 结束值（含）
     */
    public LinkedList(int valStart, int valEnd) {
        head = new ListNode(valStart);
        ListNode cur = head;
        for (int i = valStart + 1; i <= valEnd; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
    }

    /**
     * 通过数组创建链表
     * @param nums
     */
    public LinkedList(int[] nums) {
        head = new ListNode(nums[0]);
        ListNode cur = head;
        for (int i = 1; i < nums.length; i++) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }
    }


}
