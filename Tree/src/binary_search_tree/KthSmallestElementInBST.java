package binary_search_tree;

import data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class KthSmallestElementInBST {
    // 二叉搜索树中第 K 小的元素
    // 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。

    // 迭代中序遍历
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return -1; // 没有元素
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        // 中序遍历二叉树，访问节点不为空，或者栈中还有元素，就能继续遍历
        while (cur != null || !stack.isEmpty()) {
            // 首先 先中后左节点 入栈，实现先左后中的顺序遍历
            while (cur != null) { // 一直到最左节点 (树最优先访问的节点)
                stack.push(cur);
                // 倒数第K小，所以中序遍历的顺序是 左-中-右
                // 倒数第K大，顺序是 右-中-左
                cur = cur.left;
            }
            cur = stack.pop(); // 节点出栈，左-中-右的顺序寻找第k小的节点
            k--; // 剩余中序遍历次数减一
            if (k == 0) {
                break; // 当前节点就是倒数第k小的节点
            }
            // 当前节点已访问过
            // 当前右节点访问的优先级比上级的中节点高，此处入栈是插队(开始访问右边)，实现左中右(上级左)，再上级中(如果有)遍历的顺序
            cur = cur.right; // 下次右节点入栈，开始访问右边
        }
        // 倒数第k小的节点值，不足k个取最后一个节点值
        return cur.val;
    }

    // 递归 中序遍历
    int res, k;

    public int kthSmallest2(TreeNode root, int k) {
        if (k == 0) {
            return -1;
        }
        this.k = k;
        dfs(root);
        return res;
    }

    void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (k == 0) {
            return;// 已经获取到了结果
        }
        dfs(node.left);
        if (k == 0) {
            return; // 已经获取到了结果
        }
        if (--k == 0) {
            res = node.val;
            return; // 已经获取到了结果
        }
        dfs(node.right);
    }
}
