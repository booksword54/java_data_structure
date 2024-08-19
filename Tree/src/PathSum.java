import data_structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class PathSum {
    // 路径总和
    // 给你二叉树的根节点 node 和一个表示目标和的整数 targetSum 。
    // 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
    // 如果存在，返回 true ；否则，返回 false 。
    // 叶子节点 是指没有子节点的节点。

    // 递归 DFS
    public boolean hasPathSum(TreeNode node, int targetSum) {
        if (node == null) { // 递归终止条件：到达叶子节点没有找到目标和
            return false;
        }
        int nodeVal = node.val; // 当前节点的值
        // 若当前节点是叶子节点
        if (node.left == null && node.right == null) {
            // 递归终止条件：叶子节点的值跟目标值相等与否
            return nodeVal == targetSum;
        }
        // 不是叶子节点，目标和减去当前节点的值(参与进运算)，目标和和节点的值可以为负数
        targetSum -= nodeVal;
        // 分别在左右子节点寻找路径
        return hasPathSum(node.left, targetSum) || hasPathSum(node.right, targetSum);
    }

    // 迭代 BFS
    public boolean hasPathSum3(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> sumQueue = new LinkedList<>();
        nodeQueue.add(root);
        sumQueue.add(root.val);
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                int sum = sumQueue.poll(); // 父节点的基础值，到达父节点的路径和
                if (node.left == null && node.right == null) {
                    if (sum == target) {
                        return true;
                    }
                    continue;
                }
                if (node.left != null) {
                    nodeQueue.add(node.left);
                    sumQueue.add(sum + node.left.val); // 在父节点的基础值上添加本节点的值，到达本节点的路径和
                }
                if (node.right != null) {
                    nodeQueue.add(node.right);
                    sumQueue.add(sum + node.right.val);
                }
            }
        }
        return false;
    }

    //  迭代 BFS
    public boolean hasPathSum2(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> sumQueue = new LinkedList<>();
        nodeQueue.add(root);
        sumQueue.add(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int sum = sumQueue.poll();
            if (node.left == null && node.right == null) {
                if (sum == target) {
                    return true;
                }
                continue;
            }
            if (node.left != null) {
                nodeQueue.add(node.left);
                sumQueue.add(sum + node.left.val);
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
                sumQueue.add(sum + node.right.val);
            }
        }
        return false;
    }

}
