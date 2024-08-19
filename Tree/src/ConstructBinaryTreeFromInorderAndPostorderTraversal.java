import data_structure.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    // 从中序与后序遍历序列构造二叉树
    // 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。

    // 存放中序遍历的节点及其下标，便于获取子树大小 inRoot-inLeft
    Map<Integer, Integer> indexMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = postorder.length;
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        // 递归构造根节点和左右子树，返回根节点
        return helper(postorder, inorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode helper(int[] postorder, int[] inorder, int postLeft, int postRight, int inLeft, int inRight) {
        if (postLeft > postRight) { // 节点为空
            return null;
        }
        // 根节点
        TreeNode root = new TreeNode(postorder[postRight]);
        // 根节点位置
        int inRoot = indexMap.get(postorder[postRight]);
        // 左子树大小，用于确定左右子树的边界(postLeft+sizeLeft-1，postLeft+sizeLeft)
        int sizeLeft = inRoot - inLeft;
        // 构造左子树，postRight是根节点，[postLeft, postLeft+sizeLeft-1] [inLeft, inRoot - 1]是左子树节点范围
        root.left = helper(postorder, inorder, postLeft, postLeft + sizeLeft - 1, inLeft, inRight - 1);
        // 构造左子树，postRight是根节点，[postLeft+sizeLeft, postRight-1] [inRoot+1, inRight]是左子树节点范围
        root.right = helper(postorder, inorder, postLeft + sizeLeft, postRight - 1, inRoot + 1, inRight);
        return root;
    }
}
