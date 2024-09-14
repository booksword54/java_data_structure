package linked_list;

import data_structure.ListNode;

public class KthNodeFromEndOfList {
    // 返回倒数第 k 个节点
    // 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
    // 双指针
    public int kthToLast(ListNode head, int k) {
        ListNode left = head;
        ListNode right = head;
        // 右指针前进
        while (k-- > 0) { // 移动k位置。指向第k+1个节点
            right = right.next;
        }
        // 双指针前进
        while (right != null) { // right最后为空，left指向的位置就是倒数第k个节点
            left = left.next;
            right = right.next;
        }
        return left.val;
    }
}
