import data_structure.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    // 从前序与中序遍历序列构造二叉树
    // 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。

    Map<Integer, Integer> indexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return helper(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode helper(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) { // 不构成树
            return null;
        }
        int inRoot = indexMap.get(preorder[preLeft]);
        TreeNode root = new TreeNode(preorder[preLeft]);
        int sizeLeft = inRoot - inLeft;
        root.left = helper(preorder, inorder, preLeft + 1, preLeft + sizeLeft, inLeft, inRoot - 1);
        root.right = helper(preorder, inorder, preLeft + sizeLeft + 1, preRight, inRoot + 1, inRight);
        return root;
    }
}
