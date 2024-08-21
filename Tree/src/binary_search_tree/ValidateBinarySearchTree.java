package binary_search_tree;

import data_structure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class ValidateBinarySearchTree {
    // 验证二叉搜索树
    // 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
    // 有效 二叉搜索树定义如下：
    // - 节点的左子树只包含 小于 当前节点的数。
    // - 节点的右子树只包含 大于 当前节点的数。
    // - 所有左子树和右子树自身必须也是二叉搜索树。

    // 中序遍历 迭代
    public boolean isValidBST(TreeNode root) {
        double pre = -Double.MAX_VALUE; // 确保 Integer.MIN_VALUE是最左节点时，能通过cur.val > pre
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 先中后左
            while (cur != null) { // 最左边节点(最优先访问)
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            // 必须大于前面节点
            if (cur.val <= pre) {
                return false;
            }
            pre = cur.val;
            // 右边插队，左中右，开始访问右边
            cur = cur.right;
        }
        return true;
    }

    // 中序遍历 递归
    TreeNode pre;
    boolean flag = true;
    public boolean isValidBST2(TreeNode root) {
        inorder(root);
        return flag;
    }

    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        if (!flag) {
            return; // 确定不合规，向上级返回，直到返回根节点，退出inorder
        }
        if (pre != null && pre.val >= node.val) {
            flag = false; // 确定不合规
            return; // 不在访问右边
        }
        pre = node; // 记录前序节点
        inorder(node.right);
    }

    // 递归
    public boolean isValidBST3(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        // 左子树 < node && 右子树 > node
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }
}
