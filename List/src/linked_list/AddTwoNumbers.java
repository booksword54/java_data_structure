package linked_list;

import data_structure.ListNode;

public class AddTwoNumbers {
    // 两数相加
    // 给你两个 非空 的链表，表示两个非负的整数。
    // 它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
    // 请你将两个数相加，并以相同形式返回一个表示和的链表。
    // 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // 虚拟头结点，指向表示和的链表的尾节点
        ListNode cur = dummy;
        int carry = 0; // 进位
        while (l1 != null || l2 != null) {
            int x = (l1 == null ? 0 : l1.val);
            int y = (l2 == null ? 0 : l2.val);
            int sum = x + y + carry;

            carry = sum / 10; // 进位
            sum = sum % 10;

            cur.next = new ListNode(sum);
            cur = cur.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) { // 有进位，新增一个结点
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }
}
