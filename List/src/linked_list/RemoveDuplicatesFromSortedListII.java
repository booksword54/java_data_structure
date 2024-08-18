package linked_list;

import data_structure.ListNode;

public class RemoveDuplicatesFromSortedListII {
    // 删除排序链表中的重复元素 II
    // 给定一个已排序的链表的头 head，删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            int val = cur.next.val;
            if (cur.next.next.val == val) { // 相邻两个元素重复
                while (cur.next != null && cur.next.val == val) { // 移除所有等于重复值的节点
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next; // 因为是有序的，所以第一个元素必定不存在重复，但是不确定第二个和第三个节点是否重复，需要接着判断...
            }
        }
        return dummy.next;
    }
}
