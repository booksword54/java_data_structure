import data_structure.TreeNode;

public class BinaryTreeMaximumPathSum {
    // 二叉树中的最大路径和
    // 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
    // 路径和 是路径中各节点值的总和。
    // 给你一个二叉树的根节点 root ，返回其 最大路径和 。

    int max = Integer.MIN_VALUE;

    // 递归，返回值很重要
    public int maxPathSum(TreeNode root) {
        maxGain(root); // 递归本身是计算当前节点为首的最大半路径和（当前节点的值 + 左&右半路径和的较大者）
        // 递归过程中比较每个节点为中心的路径和的最大值（当前节点的值 + 左半路径最大和 + 右半最大路径和），递归的返回值对业务是有用的
        return max;
    }

    // DFS的过程中不断统计最大路径和，递归的返回值对业务是有用的，返回值很重要
    private int maxGain(TreeNode node) {
        if (node == null) { // 递归终止条件：节点为空，不计算当前节点的最大路径和，节点为首的半路径的最大和是0
            return 0;
        }
        int leftHalf = Math.max(maxGain(node.left), 0); // 左子节点为头的半个路径和最大值，小于0取0，表示不算上
        int rightHalf = Math.max(maxGain(node.right), 0); // 右子节点为头的半个路径和最大值，小于0取0，表示不算上

        // ------------------------------- DFS的业务逻辑 -------------------------------
        int sum = node.val + leftHalf + rightHalf; // 当前节点为中心的路径和的最大值
        max = Math.max(max, sum); // 比较每个节点为中心的路径和的最大值，因为会遍历完所有节点，不用考虑比较顺序，最大路径和一定会比较出来的
        // ------------------------------- DFS的业务逻辑 -------------------------------

        return node.val + Math.max(leftHalf, rightHalf); // 当前节点为头的半个路径和最大值
    }
}
