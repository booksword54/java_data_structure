package binary_search_tree;

import data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumAbsoluteDifferenceInBST {
    // 二叉搜索树的最小绝对差
    // 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
    // 差值是一个正数，其数值等于两值之差的绝对值。

    // 递归中序遍历
    int ans;
    int pre;

    public int getMinimumDifference(TreeNode root) {
        ans = Integer.MAX_VALUE; // 不影响比较最小值的结果
        pre = Integer.MAX_VALUE; // 无意义的值，表示前一个节点不存在
        inorder(root); // 最小差值只会出现在相邻节点之间，二叉搜索树的性质
        return ans;
    }

    private void inorder(TreeNode root) {
        if (root == null) { // 递归终止条件：节点为空
            return;
        }
        // 中序遍历，按照顺序二叉搜索树中取值，比较的都是大小相邻的节点
        inorder(root.left);
        // 存在前一个节点
        if (pre != Integer.MAX_VALUE) {
            // 计算当前节点与前一个节点的差值，与之前的最小值比较
            ans = Math.min(ans, root.val - pre);
        }
        // 前一个节点改成当前节点，中序遍历下一个元素
        pre = root.val;
        inorder(root.right);
    }

    // 迭代中序遍历
    public int getMinimumDifference2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        int res = Integer.MAX_VALUE;
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) { // 获取最左节点
                stack.push(cur); // 左边入栈，先访问节点的左边元素
                cur = cur.left;
            }
            cur = stack.pop(); // 弹出栈，左-当前-右 的顺序
            if (pre != null) {
                res = Math.min(res, cur.val - pre.val);
            }
            pre = cur; // 当前节点(中间)已经访问过
            cur = cur.right; // 下次右边入栈，访问右边
        }
        return res;
    }
}
