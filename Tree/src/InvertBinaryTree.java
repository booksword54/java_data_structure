import data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {
    // 翻转二叉树
    // 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 分别反转左右子树
        TreeNode left = invertTree(root.left); // 递归执行翻转左右子树，m左右子树互换的操作
        TreeNode right = invertTree(root.right); // 递归执行翻转左右子树，m左右子树互换的操作
        // 左右子树位置互换
        root.left = right;
        root.right = left;
        return root;
    }

    // 深度遍历(DFS)
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); // 深度遍历
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            // 翻转操作，交换左右子节点，之后遍历的没一个节点都会执行子树的翻转，整体上实现了翻转树的效果
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        return root;
    }

    // 广度遍历(BFS)
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size(); // 层序遍历
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                // 翻转操作，交换左右子节点，之后遍历的没一个节点都会执行子树的翻转，整体上实现了翻转树的效果
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
            }
        }
        return root;
    }
}
