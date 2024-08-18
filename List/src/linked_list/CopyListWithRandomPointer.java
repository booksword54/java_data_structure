package linked_list;

import data_structure.Node;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    // 随机链表的复制
    // 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
    // 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
    // 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
    // 返回复制链表的头节点。
    // 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
    // val：一个表示 Node.val 的整数。
    // random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
    // 你的代码 只 接受原链表的头节点 head 作为传入参数。

    // 回溯 + 哈希表
    Map<Node, Node> cachedNode = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) { // 节点为空，不用拷贝，递归终止
            return null;
        }
        // 使用缓存提高效率，若已经存在节点的缓存，
        // 说明有人在处理节点的拷贝，直接获取处理的结果即可
        if (cachedNode.containsKey(head)) {
            return cachedNode.get(head);
        }
        // 拷贝节点的值
        Node newHead = new Node(head.val);
        cachedNode.put(head, newHead); // 放入缓存，由于是对象，引用关系拷贝之后会同步
        // 拷贝节点引用，next引用和random引用
        // 递归处理，每个节点的值/引用都执行此操作
        newHead.next = copyRandomList(head.next);
        newHead.random = copyRandomList(head.random);
        return newHead;
    }

    // 迭代 + 节点拆分
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        // 复制一份新的链表
        // 新链表穿插到旧的链表。新节点在旧节点的下一个，newNode = node.next
        for (Node node = head; node != null; node = node.next.next) {
            Node newNode = new Node(node.val);
            newNode.next = node.next;
            node.next = newNode;
        }
        // 复制random关系
        // 新节点在旧节点的下一个 newNode = node.next >> newRandom = random.next
        for (Node node = head; node != null; node = node.next.next) {
            Node newNode = node.next;
            newNode.random = (node.random != null ? node.random.next : null);
        }
        Node newHead = head.next;
        // 复制next关系
        // 新链表节点从旧链表中间剔除出来，next指针串联
        for (Node node = head; node != null; node = node.next) {
            Node newNode = node.next;
            node.next = node.next.next;
            newNode.next = (newNode.next != null ? newNode.next.next : null);
        }
        return newHead;
    }
}
