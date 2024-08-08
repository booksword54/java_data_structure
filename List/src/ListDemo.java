public class ListDemo {
    // 单向链表、双端链表、有序链表、双向链表。
    // 链表（Linked list）是一种常见的基础数据结构，是一种线性表，但是并不会按线性的顺序存储数据，而是在每一个节点里存到下一个节点的指针(Pointer)。每个节点包含任意的实例数据（data fields）和一或两个用来指向上一个/或下一个节点的位置的链接（"links"）
    // 使用链表结构可以克服数组链表需要预先知道数据大小的缺点，链表结构可以充分利用计算机内存空间，实现灵活的内存动态管理。但是链表失去了数组随机读取的优点，同时链表由于增加了结点的指针域，空间开销比较大。

    // 一、单向链表（Single-Linked List）
    // 单链表是链表中结构最简单的。一个单链表的节点(Node)分为两个部分，第一个部分(data)保存或者显示关于节点的信息，另一个部分存储下一个节点的地址。最后一个节点存储地址的部分指向空值。
    // - 查询一个节点的时候需要从第一个节点开始每次访问下一个节点，一直访问到需要的位置。
    // - 插入一个节点，对于单向链表，我们需要将插入位置的前节点的指针指向自己，而自己的指针指向下一个节点。
    // - 删除一个节点，我们将该节点的上一个节点的next指向该节点的下一个节点即可。

    // 1、 单向链表的具体实现
    class SingleLinkedList {
        private int size; // 链表节点的个数
        private Node head; // 头节点

        // 在链表头添加元素
        public Object addHead(Object data) {
            Node newHead = new Node(data);
            if (size > 0) {
                newHead.next = head;
            }
            head = newHead;
            size++;
            return data;
        }

        // 在链表头删除元素
        public Object deleteHead() {
            Object data = head.data;
            head = head.next;
            size--;
            return data;
        }

        // 查找指定元素，找到了返回节点Node，找不到返回null
        public Node find(Object data) {
            if (size == 0) {
                return null;
            }
            Node current = head;
            int pos = 0;
            while (pos < size) {
                if (data.equals(current.data)) {
                    return current;
                }
                current = current.next;
                pos++;
            }
            return null;
        }

        // 删除指定的元素，删除成功返回true
        public boolean delete(Object value) {
            if (size == 0) {
                return false;
            }
            Node previous = null;
            Node current = head;
            while (current.data != value) {
                if (current.next == null) {
                    return false;
                }
                previous = current;
                current = current.next;
            }
            // 如果删除的节点是第一个节点
            if (current == head) {
                head = current.next;
            } else { // 删除的节点不是第一个节点
                previous.next = current.next;
            }
            size--;
            return true;
        }

        // 判断链表是否为空
        public boolean isEmpty() {
            return size == 0;
        }

        // 链表的每个节点类
        private class Node {
            private Object data; //每个节点的数据
            private Node next; //每个节点指向下一个节点的连接

            public Node(Object data) {
                this.data = data;
            }
        }

        public SingleLinkedList() {
            size = 0;
            head = null;
        }
    }

    // 2、 用单向链表实现栈
    public class StackSingleLink {

        private SingleLinkedList linkedList;

        //添加元素
        public void push(Object data) {
            linkedList.addHead(data);
        }

        //移除栈顶元素
        public Object pop() {
            return linkedList.deleteHead();
        }

        // 判断是否为空
        public boolean isEmpty() {
            return linkedList.isEmpty();
        }

        public StackSingleLink() {
            linkedList = new SingleLinkedList();
        }
    }

    // 二、双端链表
    // 1、 双端链表的具体实现
    // 对于单项链表，我们如果想在尾部添加一个节点，那么必须从头部一直遍历到尾部，找到尾节点，然后在尾节点后面插入一个节点。这样操作很麻烦，如果我们在设计链表的时候多个对尾节点的引用，那么会简单很多。
    public class DoublePointLinkedList {
        private Node head; // 头节点
        private Node tail; // 尾节点
        private int size; // 节点的个数

        // 表头新增节点
        public void addHead(Object data) {
            Node node = new Node(data);
            if (size == 0) { //如果链表为空，那么头节点和尾节点都是该新增节点
                head = node;
                tail = node;
            } else {
                node.next = head;
                head = node;
            }
            size++;
        }

        // 链表尾新增节点
        public void addTail(Object data) {
            Node node = new Node(data);
            if (size == 0) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            size++;
        }

        // 删除头部节点，成功返回true，失败返回false
        public boolean deleteHead() {
            // 当前链表节点数为0
            if (size == 0) {
                return false;
            }
            // 当前链表节点数为1
            if (head.next == null) {
                head = null;
                tail = null;
            } else {
                head = head.next;
            }
            size--;
            return true;
        }

        public DoublePointLinkedList() {
            size = 0;
            head = null;
            tail = null;
        }

        private class Node {
            private Object data;
            private Node next;

            public Node(Object data) {
                this.data = data;
            }
        }
    }

    // 2、 用双端链表实现队列
    class QueueLinkedList {

        private DoublePointLinkedList doublePointLinkedList;

        public QueueLinkedList() {
            doublePointLinkedList = new DoublePointLinkedList();
        }

        public void insert(Object data) {
            doublePointLinkedList.addTail(data);
        }

        public void delete() {
            doublePointLinkedList.deleteHead();
        }
    }

    // 三、有序链表
    // 前面的链表实现插入数据都是无序的，在有些应用中需要链表中的数据有序，这称为有序链表。
    // 在有序链表中，数据是按照关键值有序排列的。一般在大多数需要使用有序数组的场合也可以使用有序链表。有序链表优于有序数组的地方是插入的速度（因为元素不需要移动），另外链表可以扩展到全部有效的使用内存，而数组只能局限于一个固定的大小中。
    public class OrderLinkedList {

        private Node head;

        // 插入节点，并按照从小到大的顺序排列
        public void insert(int value) {
            Node node = new Node(value);
            Node pre = null;
            Node current = head;
            while (current != null && value > current.data) {
                pre = current;
                current = current.next;
            }
            if (pre == null) {
                head = node;
                head.next = current;
            } else {
                pre.next = node;
                node.next = current;
            }
        }

        // 删除头节点
        public void deleteHead() {
            head = head.next;
        }

        public OrderLinkedList() {
            head = null;
        }

        private class Node {
            private int data;
            private Node next;

            public Node(int data) {
                this.data = data;
            }
        }
    }

    // 四、双向链表
    // 我们知道单向链表只能从一个方向遍历，那么双向链表它可以从两个方向遍历。
    class TwoWayLinkedList {

        private Node head;
        private Node tail;
        private int size;

        // 在链表头添加节点
        public void addHead(Object data) {
            Node newNode = new Node(data);
            if (size == 0) {
                head = newNode;
                tail = newNode;
            } else {
                head.prev = newNode;
                newNode.next = head;
                head = newNode;
            }
            size++;
        }

        // 在链表尾增加节点
        public void addTail(Object data) {
            Node newNode = new Node(data);
            if (size == 0) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
            }
            size++;
        }

        // 删除链表头
        public Node deleteHead() {
            if (size == 0) {
                return null;
            }
            Node tmp = head;
            head = head.next;
            head.prev = null;
            size--;
            return tmp;
        }

        // 删除链表尾
        public Node deleteTail() {
            if (size == 0) {
                return null;
            }
            Node tmp = tail;
            tail = tail.prev;
            tail.next = null;
            size--;
            return tmp;
        }

        public TwoWayLinkedList() {
            size = 0;
            head = null;
            tail = null;
        }

        private class Node {
            private Object data;
            private Node next;
            private Node prev;

            public Node(Object data) {
                this.data = data;
            }
        }
    }
    // 上面我们讲了各种链表，每个链表都包括一个LinikedList对象和许多Node对象，
    // LinkedList对象通常包含头和尾节点的引用，分别指向链表的第一个节点和最后一个节点。
    // 而每个节点对象通常包含数据部分data，以及对上一个节点的引用prev和下一个节点的引用next，
    // 只有下一个节点的引用称为单向链表，两个都有的称为双向链表。next值为null则说明是链表的结尾，
    // 如果想找到某个节点，我们必须从第一个节点开始遍历，不断通过next找到下一个节点，
    // 直到找到所需要的。栈和队列可以用数组来实现，也可以用链表实现。

}
