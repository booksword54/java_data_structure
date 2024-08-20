import data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinarySearchTreeIterator {
    // 二叉搜索树迭代器
    // 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
    // - BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
    // - boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
    // - int next()将指针向右移动，然后返回指针处的数字。
    // 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
    // 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。

    // 中序遍历
    class BSTIterator {
        private int idx;
        private List<Integer> arr;

        public BSTIterator(TreeNode root) {
            idx = 0;
            arr = new ArrayList<>();
            inorderTraversal(root, arr);
        }

        public int next() {
            return arr.get(idx++);
        }

        public boolean hasNext() {
            return idx < arr.size();
        }

        private void inorderTraversal(TreeNode root, List<Integer> arr) {
            if (root == null) {
                return;
            }
            inorderTraversal(root.left, arr);
            arr.add(root.val);
            inorderTraversal(root.right, arr);
        }
    }

    // 迭代
    class BSTIterator2 {

        TreeNode cur;
        Deque<TreeNode> stack;

        public BSTIterator2(TreeNode root) {
            cur = root;
            stack = new ArrayDeque<>();
        }

        // 获取二叉搜索树的一个元素
        public int next() {
            // 其实是一次中序遍历
            while (cur != null) {
                stack.push(cur);
                cur = cur.left; // 左
            }
            cur = stack.pop(); // 中
            int res = cur.val;
            cur = cur.right; // 右
            return res;
        }

        // cur不是空(身为根节点，右边还有)或者stack还有元素(身为左子节点，中间还有)
        public boolean hasNext() {
            return cur != null || !stack.isEmpty();
        }
    }
}

