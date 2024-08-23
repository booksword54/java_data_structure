import data_structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SumRootToLeafNumbers {
    // 求根节点到叶节点数字之和
    // 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
    // 每条从根节点到叶节点的路径都代表一个数字：
    // - 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
    // 计算从根节点到叶节点生成的 所有数字之和 。
    // 叶节点 是指没有子节点的节点。

    // 递归 DFS
    public int sumNumbers(TreeNode root) {
        int sum = 0;
        // 根节点的路径和
        return dfs(root, sum);
    }

    private int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        // 根节点的基础值
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        // 左子节点的路径和 + 右子节点的路径和
        return dfs(root.left, sum) + dfs(root.right, sum);
    }

    // 迭代 BFS
    public int sumNumbers2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> sumQueue = new LinkedList<>();
        nodeQueue.add(root);
        sumQueue.add(root.val);
        int total = 0;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int sum = sumQueue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            // 到达叶子节点，总和添加加上该叶子节点的路径和
            if (left == null && right == null) {
                total += sum;
                continue;
            }
            // 路径和累积
            if (left != null) {
                nodeQueue.add(left);
                sumQueue.add(sum * 10 + left.val);
            }
            if (right != null) {
                nodeQueue.add(right);
                sumQueue.add(sum * 10 + right.val);
            }
        }
        return total;
    }

    // 迭代 BFS
    public int sumNumbers3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> sumQueue = new LinkedList<>();
        nodeQueue.add(root);
        sumQueue.add(root.val);
        int total = 0;
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                int sum = sumQueue.poll();
                TreeNode left = node.left;
                TreeNode right = node.right;
                // 到达叶子节点，总和添加加上该叶子节点的路径和
                if (left == null && right == null) {
                    total += sum;
                    continue;
                }
                // 路径和累积
                if (left != null) {
                    nodeQueue.add(left);
                    sumQueue.add(sum * 10 + left.val);
                }
                if (right != null) {
                    nodeQueue.add(right);
                    sumQueue.add(sum * 10 + right.val);
                }
            }
        }
        return total;
    }
}
