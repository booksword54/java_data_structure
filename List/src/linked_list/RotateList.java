package linked_list;

import data_structure.ListNode;

public class RotateList {
    // 旋转链表
    // 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
    // 多次反转链表
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        // 获取链表长度
        int len = 1;
        ListNode cur = head;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }
        // 获取旋转点的位置
        int index = k % len;
        if (index == 0) { // 无需旋转
            return head;
        }
        // 整体翻转
        ListNode leftHead = reverseLinkedList(head);
        ListNode leftTail = leftHead;
        for (int i = 0; i < index - 1; i++) {
            leftTail = leftTail.next;
        }
        ListNode rightHead = leftTail.next;
        // 断开两链表的链接
        leftTail.next = null;
        // 两链表分别反转
        ListNode newLeftHead = reverseLinkedList(leftHead);
        ListNode newRightHead = reverseLinkedList(rightHead);
        // 拼接旋转后的链表
        ListNode newLeftTail = newLeftHead;
        for (int i = 0; i < index - 1; i++) {
            newLeftTail = newLeftTail.next;
        }
        // 重建两链表的链接
        newLeftTail.next = newRightHead;
        return newLeftHead;
    }

    private ListNode reverseLinkedList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next; // 记录下一个要反转的位置，cur是本次反转要操作的节点
            // 关键步骤：pre和cur之间的指针反转
            cur.next = pre; // ① pre和cur之间的指针反转
            pre = cur; // ② 前一个节点指向当前节点
            cur = next; // ③ 当前节点指向下一个节点
        }
        return pre;
    }
}
