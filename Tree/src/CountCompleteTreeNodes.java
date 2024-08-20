import data_structure.TreeNode;

public class CountCompleteTreeNodes {
    // 完全二叉树的节点个数
    // 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
    // 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。

    // 利用完全二叉树的特性，简化计算
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countLevel(root.left); // 左右子树的层数
        int right = countLevel(root.right); // 左右子树的层数
        if (left == right) { // 层数相等，左子树完整(1 << left)，右子树单独计算节点个数
            return countNodes(root.right) + (1 << left);
        } else { // 层数不等，右子树完整，左子树单独计算节点个数
            return countNodes(root.left) + (1 << right);
        }
    }

    // 计算数的层数
    private int countLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left; // 左边界是最深的
        }
        return level;
    }
}
