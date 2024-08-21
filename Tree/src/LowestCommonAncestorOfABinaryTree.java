import data_structure.TreeNode;

public class LowestCommonAncestorOfABinaryTree {
    // 二叉树的最近公共祖先
    // 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
    // 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，
    // 最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

    // 逻辑就是一直向上级返回查找的结果，到了根节点统计出来公共祖先是哪个节点
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) { // 递归终止条件：向上级返回 null
            return null;
        }
        if (root == p || root == q) { // 递归终止条件：找到了p，q，向上级返回
            return root;
        }
        // 在左边寻找p或q或公共节点，返回找到的节点
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 在右边寻找p或q或公共节点，返回找到的节点
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) { // 两边都不存在，向上级返回 null
            return null;
        }
        if (left == null) { // 右边找到了p、q或者公共节点，向上级返回
            return right;
        }
        if (right == null) { // 左边找到了p、q或者公共节点，向上级返回
            return left;
        }
        return root; // 接到下级结果，左右同时找到了p、q，根节点就是公共节点，向上级返回根节点
    }
}
