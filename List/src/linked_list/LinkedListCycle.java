package linked_list;

import data_structure.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
    // 环形链表
    // 给你一个链表的头节点 head ，判断链表中是否有环。
    // 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
    // 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
    // 注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
    // 如果链表中存在环 ，则返回 true 。 否则，返回 false 。


    // 快慢指针
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                // 遍历到链表末尾，没有环路，提前结束
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true; // slow和fast相遇，出现环路
    }

    // 哈希表
    public boolean hasCycle2(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        while (head != null) {
            if (!seen.add(head)) { // 节点已经添加过，证明出现环
                return true;
            }
            head = head.next; // 添加下一个节点
        }
        return false;
    }

}
