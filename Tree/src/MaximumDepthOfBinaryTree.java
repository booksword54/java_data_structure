import data_structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfBinaryTree {
    // 叉树的最大深度
    // 给定一个二叉树 root ，返回其最大深度。
    // 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。

    // 递归
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    // 迭代(BFS)
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size(); // 层序遍历
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ans++;
        }
        return ans;
    }
}
