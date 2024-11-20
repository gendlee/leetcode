package linkedlist;

public class LinkedList {
    ListNode head;

    public LinkedList(int valStart, int valEnd) {
        head = new ListNode(valStart);
        ListNode cur = head;
        for (int i = valStart + 1; i <= valEnd; i++) {
            ListNode node = new ListNode(i);
            cur.setNext(node);
            cur = cur.getNext();
        }
    }

    public ListNode getHead() {
        return head;
    }
}
