import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.*;

public class QueueDemo {
    // 队列（queue）是一种特殊的线性表，特殊之处在于它只允许在表的前端（front）进行删除操作，而在表的后端（rear）进行插入操作，和栈一样，队列是一种操作受限制的线性表。进行插入操作的端称为队尾，进行删除操作的端称为队头。队列中没有元素时，称为空队列。
    // 队列的数据元素又称为队列元素。在队列中插入一个队列元素称为入队，从队列中删除一个队列元素称为出队。因为队列只允许在一端插入，在另一端删除，所以只有最早进入队列的元素才能最先从队列中删除，故队列又称为先进先出（FIFO—first in first out） 线性表。
    // 队列的容量可以有限，也可以是无限的。

    // 队列分为：
    // 1、 单向队列（Queue）：只能在一端插入数据，另一端删除数据。
    // 2、 双向队列（Deque）：每一端都可以进行插入数据和删除数据操作。

    // 队列的实现
    // 一、基于数组的Queue实现
    // 一般情况下，对于Queue而言，最核心的操作是：插入队列(enqueue)、移出队列(dequeue)。因为在队列中，插入操作是插入到队列的最后，而移出操作是移出队列的头部元素。
    // 因此我们通常会使用两个变量 front（队头指针） 和 rear（队尾指针） 记录当前元素的位置。
    // 当我们要插入一个元素时，因为总是插入到队列的最尾部，所以插入的位置是rear+1的位置。
    // 当我们要移出一个元素时，是从队头指针front的位置开始移除(因为Queue头部的元素是最先加入进来的，根据FIFO原则，应该最先移除)。当移除一个元素之后，front应该加1，因为移出一个元素之后，下一个元素就变成了第一个元素

    // 涉及到了循环队列的概念。也就是当队尾指针到了数组的最后一个下标时，下一个位置应该就是数组的首部。
    // 因此，当队尾指针指向数组顶端的时候，我们要将队尾指针(rear)重置为-1，此时再加1，就是0，也就是数组顶端例。
    class MyQueue {
        private Object[] queue;
        // 队列总大小
        private int maxSize;
        // 前端
        private int front;
        // 后端
        private int rear;
        // 队列中元素的实际数目
        private int size;

        public MyQueue(int s) {
            queue = new Object[maxSize];
            maxSize = s;
            front = 0;
            rear = -1;
            size = 0;
        }

        // 队列中新增数据
        public void insert(int value) {
            if (isFull()) {
                System.out.println("队列已满");
                return;
            }
            if (rear == maxSize - 1) {
                rear = -1;
            }
            queue[++rear] = value;
            size++;
        }

        // 移除数据
        public Object remove() {
            if (isEmpty()) {
                System.out.println("队列是空");
                return null;
            }
            Object removeValue = queue[front];
            queue[front++] = null;
            if (front == maxSize) {
                front = 0;
            }
            size--;
            return removeValue;
        }

        // 查看对头数据
        public Object peek() {
            return queue[front];
        }

        // 判断队列是否满了
        public boolean isFull() {
            return (size == maxSize);
        }

        // 判断队列是否为空
        public boolean isEmpty() {
            return size == 0;
        }

    }

    // 二、java中的队列Queue
    // ConcurrentLinkedQueue：
    public void testConcurrentLinkedQueue() {
        // - ConcurrentLinkedQueue是由链表结构组成的线程安全的先进先出无界队列。
        // - 当多线程要共享访问集合时，ConcurrentLinkedQueue是一个比较好的选择。
        // - 不允许插入null元素
        // - 支持非阻塞地访问并发安全的队列，不会抛出ConcurrentModifiationException异常。
        ConcurrentLinkedQueue<Integer> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        // insert
        boolean add = concurrentLinkedQueue.add(1);
        boolean offer = concurrentLinkedQueue.offer(2);
        // examine
        Integer element = concurrentLinkedQueue.element();
        Integer peek = concurrentLinkedQueue.peek();
        // delete
        boolean remove = concurrentLinkedQueue.remove(1);
        Integer poll = concurrentLinkedQueue.poll();
    }

    // 三、java中的双端队列(Deque)
    // Deque继承自Queue接口，也可以作为单端队列使用
    // LinkedList
    public void testDeque() {
        Deque<Integer> deque = new LinkedList<>();
        // insert
        deque.addFirst(1);
        deque.addLast(2);
        boolean offerFirst = deque.offerFirst(1);
        boolean offerLast = deque.offerLast(2);
        // examine
        Integer getFirst = deque.getFirst();
        Integer getLast = deque.getLast();
        Integer peekFirst = deque.peekFirst();
        Integer peekLast = deque.peekLast();
        // delete
        Integer removeFirst = deque.removeFirst();
        Integer removeLast = deque.removeLast();
        Integer pollFirst = deque.pollFirst();
        Integer pollLast = deque.pollLast();
    }

    // 四、使用双端队列Deque实现栈的功能
    // java已经废弃了类Stack, 使用Deque定义了LIFO（后进先出） 的栈操作
    public void testDequeStack() {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(1);
        Integer pop = stack.pop();
        Integer peek = stack.peek();
    }

    // 五、阻塞队列(BlockingQueue)
    // 阻塞队列的特点就在于阻塞，它可以阻塞线程，让生产者消费者得以平衡，阻塞队列中有两个关键方法 put 和 take 方法
    // Take : 方法的功能是获取并移除队列的头结点，通常在队列里有数据的时候是可以正常移除的。可是一旦执行 take 方法的时候，队列里无数据，则阻塞，直到队列里有数据。一旦队列里有数据了，就会立刻解除阻塞状态，并且取到数据。
    // Put: 方法插入元素时，如果队列没有满，那就和普通的插入一样是正常的插入，但是如果队列已满，那么就无法继续插入，则阻塞，直到队列里有了空闲空间。如果后续队列有了空闲空间，比如消费者消费了一个元素，那么此时队列就会解除阻塞状态，并把需要添加的数据添加到队列中。
    public void testBlockingQueue() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
        blockingQueue.put(1);
        Integer take = blockingQueue.take();

        // 常见的阻塞队列成员:
        // 1、ArrayBlockingQueue: 数组结构的有界阻塞队列，此队列按照先进先出(FIFO)原则对元素进行排序，
        // 同时支持公平锁和非公平锁。它的线程安全性由 ReentrantLock 来实现的。

        // 和 ReentrantLock 一样，如果 ArrayBlockingQueue 被设置为非公平的，那么就存在插队的可能；
        // 如果设置为公平的，那么等待了最长时间的线程会被优先处理，其他线程不允许插队，
        // 不过这样的公平策略同时会带来一定的性能损耗，因为非公平的吞吐量通常会高于公平的情况。
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(10, true);
        // 如上所示，ArrayBlockingQueue 提供的构造函数中，需要指定队列的长度，同时也可以设置队列是都是公平的，当我们设置了容量后就不能再修改了，符合数组的特性，此队列按照先进先出（FIFO）的原则对元素进行排序。

         // 2、LinkedBlockingQueue: 链表结构的有界阻塞队列，队列的默认大小为 Integer.MAX_VALUE，
        // 这个值是非常大的，几乎无法达到，对此我们可以认为这个队列基本属于一个无界队列（也又认为是有界 队列）
        // 也是按照先进先出原则对元素进行排序的。它的线程安全性由 ReentrantLock 来实现的。
        // 队列头部和尾部都可以添加和移除元素，多线程并发时，可以将锁的竞争最多降到一半。
        LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>();

        // 3、PriorityBlockingQueue: 支持线程优先级排序的无界阻塞队列。默认自然序进行排序，也可以自定义实现compareTo()方法来指定元素排序规则，不能保证同优先级元素的顺序。
        // Integer实现了compareTo方法
        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();

        // 4、DelayQueue: 实现PriorityBlockingQueue优先级排序的延迟阻塞队列，在创建元素时，可以指定多久才能从队列中获取当前元素。只有延时期满后才能从队列中获取元素。
        DelayQueue<Delayed> delayQueue = new DelayQueue<>();

        // 5、SynchronousQueue: 不存储元素的阻塞队列，每一个put操作必须等待take操作，否则不能添加元素。支持公平锁和非公平锁。
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>(true);
    }

}
