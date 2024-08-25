import java.util.PriorityQueue;

public class FindMedianFromDataStream {
    PriorityQueue<Integer> min;
    PriorityQueue<Integer> max;

    public FindMedianFromDataStream() {
        min = new PriorityQueue<>((a, b) -> b - a);
        max = new PriorityQueue<>((a, b) -> a - b);
    }

    // 从队列中间往两边加数
    public void addNum(int num) {
        if (min.isEmpty() || num <= min.peek()) {
            min.add(num);
            if (min.size() > max.size() + 1) {
                max.add(min.poll());
            }
        } else {
            max.add(num);
            if (max.size() > min.size()) {
                min.add(max.poll());
            }
        }
    }

    // 寻找两个队列的中位数
    public double findMedian() {
        if (min.size() > max.size()) {
            return min.peek();
        }
        return (min.peek() + max.peek()) / 2.0;
    }
}
