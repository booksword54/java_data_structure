package array;

import java.util.Deque;
import java.util.LinkedList;

public class TrappingRainWater {
    // 接雨水
    // 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

    // 单调栈
    public int trap(int[] height) {
        int ans = 0;
        int n = height.length;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                // 与左边界所围区间的宽度差
                int widDiff = i - left - 1;
                // 与左边界所围区间的高度差
                int heiDiff = Math.min(height[left], height[i]) - height[top];
                ans += widDiff * heiDiff;
            }
            stack.push(i);
        }
        return ans;
    }
}
