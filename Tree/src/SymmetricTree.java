import data_structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
    // 对称二叉树
    // 给你一个二叉树的根节点 root ， 检查它是否轴对称。

    // 递归
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 节点的左子树和右子树是否对称
        return check(root.left, root.right);
    }

    private boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        // 判断两节点的左子树和右子树。右子树和左子树是否成对称关系，依次递归
        return check(p.left, q.right) && check(p.right, q.left);
    }

    // 迭代 DFS
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check2(root.left, root.right);
    }

    private boolean check2(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            // 判断每一个节点的左右子树是否对称
            TreeNode pNode = queue.poll();
            TreeNode qNode = queue.poll();
            if (pNode == null && qNode == null) {
                continue;
            }
            if (pNode == null || qNode == null) {
                return false;
            }
            if (pNode.val != qNode.val) {
                return false;
            }
            // 对称位置的元素成对出现
            queue.add(pNode.left);
            queue.add(qNode.right);
            // 对称位置的元素成对出现
            queue.add(pNode.right);
            queue.add(qNode.left);
        }
        return true;
    }

    // 迭代 DFS
    public boolean isSymmetric3(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check3(root.left, root.right);
    }

    private boolean check3(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 判断每一层的节点左右子树是否对称
            for (int i = 0; i < size; i++) {
                // 判断对称位的节点是否相同
                TreeNode pNode = queue.poll();
                TreeNode qNode = queue.poll();
                if (pNode == null && qNode == null) {
                    continue;
                }
                if (pNode == null || qNode == null) {
                    return false;
                }
                if (pNode.val != qNode.val) {
                    return false;
                }
                // 对称位置的元素成对出现
                queue.add(pNode.left);
                queue.add(qNode.right);
                // 对称位置的元素成对出现
                queue.add(pNode.right);
                queue.add(qNode.left);
            }
        }
        return true;
    }
}
