package linked_list;

import data_structure.ListNode;

public class ReverseNodesInKGroup {
    // K 个一组翻转链表
    // 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
    // k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
    // 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy; // 某组节点的头节点的前一个节点
        ListNode end = dummy; // 某组节点的尾节点
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next; // 寻找该组的尾节点
            }
            if (end == null) {
                break; // 该组节点数不足k个，不用再反转了
            }
            // 记录该组节点的上一个(pre)和下一个节点(next)，重建头尾节点的连接时使用
            ListNode next = end.next; // 尾节点的下一个节点

            // 限定翻转链表的范围 [start, end]
            ListNode start = pre.next; // 头节点，限定翻转链表的范围。第一个的位置是start(从start开始反转)，start会反转成尾节点
            end.next = null; // 尾节点断连，限定翻转链表的范围。最后的位置是end(断连后，end后的元素访问不了)，end会反转成头节点

            // 反转[start, end]间的链表
            reverseLinkedList(start);

            // 新的头尾节点生效
            pre.next = end; // pre与新的头节点建立连接
            start.next = next; // 新的尾节点与next建立连接

            // 为下一组反转做准备
            pre = start; // 下一组节点的头节点的前一个节点
            end = pre; // 初始化下一组节点的尾节点
        }
        return dummy.next;
    }

    // 通过三个指针反转链表，头变尾，尾变头
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
