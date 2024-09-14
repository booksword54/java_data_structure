package linked_list;

import data_structure.ListNode;

public class PartitionList {
    // 分隔链表
    // 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
    // 你应当 保留 两个分区中每个节点的初始相对位置。
    // 虚拟节点 + 双指针
    public ListNode partition(ListNode head, int x) {
        ListNode smallDummy = new ListNode(0);
        ListNode largeDummy = new ListNode(0);
        ListNode small = smallDummy;
        ListNode large = largeDummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                small.next = cur;
                small = small.next;
            } else {
                large.next = cur;
                large = large.next;
            }
            cur = cur.next;
        }
        large.next = null; // 断开原有链接
        small.next = largeDummy.next; // 拼接两个链表
        return smallDummy.next;
    }

}
