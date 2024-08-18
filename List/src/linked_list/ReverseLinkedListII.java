package linked_list;

import data_structure.ListNode;

public class ReverseLinkedListII {
    // 反转链表 II
    // 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
    // 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。

    // 一次遍历「穿针引线」反转链表（头插法）
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy; // [left, right]截取的链表头节点的上一个节点
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        // 反转链表next指针
        for (int i = 0; i < right - left; i++) { // 反转right - left 个 next指针
            next = cur.next; // 本次反转的元素
            // 关键是下面三步尾节点、头节点的转换，【记忆】指针变动轨迹是一个象形的 5 字
            cur.next = next.next; // ① cur变成了尾部节点，指向下一个要反转的元素
            next.next = pre.next; // ② next的指针指向头节点，next指向下一个节点的指针断开，改成由cur指向[①](尾节点交接)
            pre.next = next; // ③ next节点被pre.next头指针指向，变成了头节点(头节点交接)
        }
        return dummy.next;
    }

    // 穿针引线
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy; // [left, right]截取的链表头节点的上一个节点
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode rightNode = pre; // 尾节点
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }
        ListNode leftNode = pre.next; // 头节点

        ListNode next = rightNode.next; // [left, right]截取的链表头节点的下一个节点

        pre.next = null; // 断开两边的联系，头节点断开连接
        rightNode.next = null; // 断开两边的联系，尾节点断开连接
        // 翻转[left, right]所围的链表
        reverseLinkedList(leftNode);
        pre.next = rightNode; // 重新建立两边的联系，头节点建立连接
        leftNode.next = next; // 重新建立两边的联系，尾节点建立连接

        return dummy.next;
    }

    // 反转链表
    private void reverseLinkedList(ListNode head) {
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
    }
}
