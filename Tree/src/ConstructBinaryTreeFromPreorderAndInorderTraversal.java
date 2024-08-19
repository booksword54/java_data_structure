import data_structure.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    // 从前序与中序遍历序列构造二叉树
    // 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。

    // 存放中序遍历的节点及其下标，便于获取左子树大小 inRoot-inLeft
    Map<Integer, Integer> indexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        // 递归构造根节点和左右子树，返回根节点
        return helper(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode helper(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) { // 节点为空
            return null;
        }
        // 根节点
        TreeNode root = new TreeNode(preorder[preLeft]);
        // 根节点位置
        int inRoot = indexMap.get(preorder[preLeft]);
        // 左子树大小，用于确定左右子树的边界(preLeft+sizeLeft，preLeft+sizeLeft+1)
        int sizeLeft = inRoot - inLeft;
        // 构造左子树，preLeft是根节点，[preLeft+1, preLeft+sizeLeft] [inLeft, inRoot - 1]是左子树节点范围
        root.left = helper(preorder, inorder, preLeft + 1, preLeft + sizeLeft, inLeft, inRoot - 1);
        // 构造右子树，preLeft是根节点，[preLeft+sizeLeft+1, preRight] [inRoot+1, inRight]是右子树节点范围，
        root.right = helper(preorder, inorder, preLeft + sizeLeft + 1, preRight, inRoot + 1, inRight);
        return root;
    }
}
