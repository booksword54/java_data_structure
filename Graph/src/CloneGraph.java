import data_structure.Node;

import java.util.*;

public class CloneGraph {
    // 克隆图
    // 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
    // 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。

    // 深度优先遍历
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) { // 避免第一次调用空指针，不是递归终止条件
            return null;
        }
        // 如果已经拷贝过，直接获取，递归终止
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        // 拷贝当前节点
        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode); // 将拷贝的节点放入缓存
        // 递归拷贝相邻节点，深度优先遍历
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }

    // 广度优先遍历
    public Node cloneGraph2(Node root) {
        if (root == null) {
            return null;
        }
        Map<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Node cloneRoot = new Node(root.val, new ArrayList<>());
        queue.add(root);
        visited.put(root, cloneRoot);
        while (!queue.isEmpty()) {
            Node node = queue.poll(); // 当前节点
            Node cloneNode = visited.get(node); // 当前节点的克隆节点
            // 层序遍历，拷贝邻居关系
            for (Node neighbor : node.neighbors) { // 当前节点的邻居
                if (!visited.containsKey(neighbor)) { // 邻居节点没有拷贝过
                    // 加入队列,逐层拷贝
                    Node cloneNeighbor = new Node(neighbor.val, new ArrayList<>());
                    queue.add(neighbor);
                    visited.put(neighbor, cloneNeighbor);
                    // 放入拷贝克隆节点的邻居队列中
                    cloneNode.neighbors.add(cloneNeighbor);
                } else {
                    // 已经拷贝过，直接获取
                    Node cloneNeighbor = visited.get(neighbor);
                    // 放入拷贝克隆节点的邻居队列中
                    cloneNode.neighbors.add(cloneNeighbor);
                }
            }
        }
        // 克隆完毕，包括节点本身和邻居节点
        return cloneRoot;
    }
}
