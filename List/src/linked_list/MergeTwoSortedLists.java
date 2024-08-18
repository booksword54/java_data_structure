package linked_list;

import data_structure.ListNode;

public class MergeTwoSortedLists {
    // 合并两个有序链表
    // 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

    // 迭代
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1); // 虚拟头结点，指向合并链表的头部
        ListNode tail = dummy; // 尾部节点，选择两链表的较小值拼接
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next; // 尾部节点更新，是两个链表节点中较小的一个
        }
        tail.next = (l1 == null ? l2 : l1);
        return dummy.next;
    }

    // 递归
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) { // 递归结束条件，一个链表为空，直接选择另一个链表
            return l2;
        }
        if (l2 == null) { // 递归结束条件，一个链表为空，直接选择另一个链表
            return l1;
        }
        if (l1.val < l2.val) {
            // 选择l1作为尾部节点，l1.next就是合并链表的点尾部节的next节点，
            l1.next = mergeTwoLists(l1.next, l2); // 从剩余的元素中不断选择较小值。连接到链表尾端
            return l1;
        } else {
            // 选择l2，l2.next就是合并链表的点尾部节的next节点，
            l2.next = mergeTwoLists(l1, l2.next); // 从剩余的元素中不断选择较小值。连接到链表尾端
            return l2;
        }
    }
}
