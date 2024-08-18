import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class MinStack {
    // 最小栈
    // 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
    // 实现 MinStack 类:
    // - MinStack() 初始化堆栈对象。
    // - void push(int val) 将元素val推入堆栈。
    // - void pop() 删除堆栈顶部的元素。
    // - int top() 获取堆栈顶部的元素。
    // - int getMin() 获取堆栈中的最小元素。

    Deque<Integer> stack;
    Deque<Integer> minStack;

    public MinStack() {
        stack = new LinkedList<>(); // 存放每个元素
        minStack = new LinkedList<>(); // 存放每个元素对应的的最小值
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            minStack.push(Math.min(minStack.peek(), val));
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        stack.pop();
        minStack.pop();
    }

    public int top() {
        if (stack.isEmpty()) {
            throw new RuntimeException("栈中元素为空，此操作非法");
        }
        return stack.peek();
    }

    public int getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("栈中元素为空，此操作非法");
        }
        return minStack.peek();
    }
}
