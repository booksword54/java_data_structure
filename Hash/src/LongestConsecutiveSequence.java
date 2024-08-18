import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    // 最长连续序列
    // 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
    // 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num); // 记录所有元素
        }
        int maxLen = 0;
        for (Integer num : set) {
            if (!set.contains(num - 1)) { // 可以充当连续区间的起始元素
                int curr = num;
                int len = 1; // 连续区间长度
                while (set.contains(curr + 1)) { // 后继有元素
                    curr++;
                    len++;
                }
                // 比较获取最长连续区间
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }
}
