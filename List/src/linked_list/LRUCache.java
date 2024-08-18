package linked_list;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    // LRU 缓存
    // 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
    // 实现 LRUCache 类：
    // - LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
    // - int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
    // - void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
    // 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode() {
        }

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<>(); // LRU缓存
    private int size; // 缓存大小
    private int capacity; // 缓存容量
    private DLinkedNode head, tail; // LRU链表头尾节点

    // 哈希表 + 双向链表
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode(); // 虚拟头部
        tail = new DLinkedNode(); // 虚拟尾部
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key); // 从缓存中获取节点
        if (node == null) {
            return -1; // 不存在
        }
        moveToHead(node); // 最新访问的节点移动到LRU链表头部
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) { // 缓存中不存在，执行新增逻辑
            DLinkedNode newNode = new DLinkedNode(key, value);
            cache.put(key, newNode); // 放入缓存
            addToHead(newNode); // 新增元素到LRU链表头部，是最近访问的
            size++;
            // 超出容量大小，执行淘汰
            if (size > capacity) {
                // 淘汰尾部元素
                DLinkedNode tail = removeTail(); // 从LRU链表尾部选取最少使用的元素
                cache.remove(tail.key); // 清理缓存
                size--;
            }
        } else { // 缓存存在，执行更新逻辑
            node.value = value;
            moveToHead(node); // 最新访问的节点放到头部
        }
    }

    // 新增的元素添加到头部
    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // 新访问、更新的元素移动到头部
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    // 淘汰尾部节点
    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    // 移动节点之前先删除节点，淘汰尾部节点的时候删除节点
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

}
