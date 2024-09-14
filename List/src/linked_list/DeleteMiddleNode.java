package linked_list;

import data_structure.ListNode;

public class DeleteMiddleNode {
    // 删除中间节点
    // 若链表中的某个节点，既不是链表头节点，也不是链表尾节点，则称其为该链表的「中间节点」。
    // 假定已知链表的某一个中间节点，请实现一种算法，将该节点从链表中删除。
    public void deleteNode(ListNode node) {
        // 中间节点不是尾节点，值变更为下一个节点
        node.val = node.next.val;
        // 跳过下一个节点，中间节点充当了下一个节点，不是他自己，相当于删除掉了
        node.next = node.next.next;
    }
}
