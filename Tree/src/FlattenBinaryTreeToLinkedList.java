import data_structure.TreeNode;

public class FlattenBinaryTreeToLinkedList {
    // 二叉树展开为链表
    // 给你二叉树的根结点 root ，请你将它展开为一个单链表：
    // - 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
    // - 展开后的单链表应该与二叉树 先序遍历 顺序相同。

    TreeNode pre; // 记录子树展开链表的头节点

    // 递归法
    public void flatten(TreeNode root) {
        if (root == null) { // 递归终止条件，root为空时不再平整化
            return;
        }
        // 必须先右后左
        flatten(root.right); // 平整化右子树
        flatten(root.left); // 平整化左子树
        // 到这里 pre 指向的是左子树展开链表的头节点
        root.right = pre; // 右指针指向 前一个平整化左子树的头节点，
        root.left = null; // 左子树为空
        pre = root; // 记录树平整化后的链表的头节点，是root，树先序遍历第一个节点是root
    }

    // 迭代法
    public void flatten2(TreeNode root) {
        while (root != null) {
            // 一直遍历右边子树的节点，遇到左子节点，然后将其与纯右子节点构成的链表拼接到总链表
            // 直到将左子节点全部转换到链表里面
            if (root.left != null) {
                // 把左节点及其纯右子节点构成的链表拼接到链表
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root.right; // 展开链表的尾节点，即先序遍历的最后一个节点
                root.right = root.left; // root.left是左子树的根节点，根节点是展开链表的头节点，即先序遍历第一个节点
                root.left = null;
            }
            root = root.right;
        }
    }
}
