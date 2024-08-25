import java.util.Comparator;
import java.util.PriorityQueue;

public class KthLargestElementInAnArray {
    // 数组中的第K个最大元素
    // 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
    // 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
    // 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。

    // 优先队列 堆
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (a, b) -> a - b);
        for (int i = 0; i < k; i++) {
            minHeap.add(nums[i]);
        }
        for (int i = k; i < len; i++) {
            int min = minHeap.peek();
            if (nums[i] > min) {
                minHeap.poll();
                minHeap.add(nums[i]);
            }
        }
        return minHeap.peek();
    }
}
