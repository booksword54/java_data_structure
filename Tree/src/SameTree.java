import data_structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SameTree {
    // 相同的树
    // 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
    // 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

    // 递归
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) { // 同时为空，判断下一对
            return true;
        }
        if (p == null || q == null) { // 出现不同
            return false;
        }
        if (p.val != q.val) { // 出现不同
            return false;
        }
        // 到此处根节点相同
        // 分别判断左右子树是否相同
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // 迭代 BFS
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            // 成对获取相同位置的元素
            TreeNode pNode = queue.poll();
            TreeNode qNode = queue.poll();
            if (pNode == null && qNode == null) { // 同时为空，判断下一对
                continue;
            }
            if (pNode == null || qNode == null) { // 出现不同
                return false;
            }
            if (pNode.val != qNode.val) { // 出现不同
                return false;
            }
            // 成对添加相同位置的元素
            queue.add(pNode.left);
            queue.add(qNode.left);
            queue.add(pNode.right);
            queue.add(qNode.right);
        }
        return true;
    }
}
