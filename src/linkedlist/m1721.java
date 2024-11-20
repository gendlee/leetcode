package linkedlist;

import utils.Printer;

public class m1721 {
    int midIndex = 1;
    int length = 0;
    public ListNode swapNodes(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }
        // 找到链表的中间点
        ListNode midNode = findMidNode(head);
        if (midIndex == k && length%2 == 1 && midNode.next != null) {
            return head;
        }
        boolean overHalf = k >= midIndex;

        // 分割成两条链表
        ListNode firstEndNode = splitInto2List(head, midNode);

        ListNode secondHead = midNode;
        // 左右两条链表，反转右边一条链表
        secondHead = reverseList(secondHead);

        ListNode cur1 = head;
        ListNode cur2 = secondHead;
        if (overHalf) {
            k = length - k + 1;
        }
        System.out.println(length);
        // 两条链表同时从头开始向前走K步
        if (cur1.next != null && cur2.next != null) {
            for (int i = 1; i < k; i++) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
        }

        // 交换节点值
        int tmp = cur1.val;
        cur1.setVal(cur2.val);
        cur2.setVal(tmp);

        // 再反转第二条链表
        secondHead = reverseList(secondHead);

        // 接上链表
        firstEndNode.next = secondHead;

        return head;
    }

    private ListNode reverseList(ListNode head) {
        ListNode preNode = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextNode = cur.next;
            cur.next = preNode;
            preNode = cur;
            cur = nextNode;
        }
        return preNode;
    }

    private ListNode findMidNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null)  {
            fast = fast.next.next;
            slow = slow.next;
            midIndex++;
            length++;
        }
        ListNode cur = slow;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return slow;
    }

    /**
     * 返回第一个链表的最后一条节点
     */
    private ListNode splitInto2List(ListNode head, ListNode midHead) {
        ListNode cur = head;
        while (cur.next != midHead) {
            cur = cur.next;
        }
        // 切断中间连接
        cur.next = null;
        return cur;
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList(1, 20);
        ListNode head = linkedList.getHead();

        Printer.printLinkedList(head);

        m1721 m = new m1721();

        m.swapNodes(head, 11);

        Printer.printLinkedList(head);

    }

}
