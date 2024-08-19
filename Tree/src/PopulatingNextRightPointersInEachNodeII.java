import data_structure.Node;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersInEachNodeII {
    // 填充每个节点的下一个右侧节点指针 II
    // 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
    // 初始状态下，所有 next 指针都被设置为 NULL 。

    // 层序遍历 BFS
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node tail = null; // 每层节点的尾节点
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                // 跳过为空的子节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                // 层序遍历，通过next指针连接该层的每个节点
                if (i == 0) {
                    tail = node;
                } else {
                    tail.next = node;
                    tail = node;
                }
            }
        }
        return root;
    }
}
