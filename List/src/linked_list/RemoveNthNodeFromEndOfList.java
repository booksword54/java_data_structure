package linked_list;

import data_structure.ListNode;

public class RemoveNthNodeFromEndOfList {
    // 删除链表的倒数第 N 个结点
    // 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

    // 双指针
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode left = dummy;
        ListNode right = dummy;
        for (int i = 0; i < n + 1; i++) { // 移动 n + 1 步，最后left指向倒数第 n + 1 个节点，是删除节点的前一个节点
            right = right.next;
        }
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        // 倒数第 n + 1 个节点的指针跳过了要倒数第 n 个节点
        left.next = left.next.next;
        return dummy.next;
    }
}
