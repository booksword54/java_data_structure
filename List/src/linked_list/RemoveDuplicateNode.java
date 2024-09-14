package linked_list;

import data_structure.ListNode;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicateNode {
    // 移除重复节点
    // 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。

    // 哈希表
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<Integer> set = new HashSet<>();
        set.add(head.val);
        ListNode pos = head;
        while (pos.next != null) {
            ListNode cur = pos.next;
            if (set.add(cur.val)) {
                // 当前指针指向下一个不重复的元素
                pos = pos.next;
            } else {
                // 忽略下一个重复的元素
                pos.next = pos.next.next;
            }
        }
        return head;
    }

    // 双重循环
    // 一种不使用临时缓冲区（也就是方法一中的哈希表）的方法
    public ListNode removeDuplicateNodes2(ListNode head) {
        ListNode left = head;
        while (left != null) {
            // 跳过与left相同的元素
            ListNode right = left;
            while (right.next != null) {
                // 跳过下一个与left相同的元素
                if (right.next.val == left.val) {
                    right.next = right.next.next;
                } else {
                    // 记录下一个不相同的元素
                    right = right.next;
                }
            }
            // 遍历下一个位置不为left的元素
            left = left.next;
        }
        return head;
    }
}
