public class JumpGameII {
    // 跳跃游戏 II
    // 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
    // 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
    // - 0 <= j <= nums[i]
    // - i + j < n
    // 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。

    // 贪心
    public int jump(int[] nums) {
        int len = nums.length;
        int step = 0;
        int end = 0, max = 0;
        for (int i = 0; i < len - 1; i++) {
            // 生成用例一定能到达终点，不存在 i > max 的情况
            max = Math.max(max, i + nums[i]);
            if (i == end) { // 到达了上一次跳能到达的最远终点
                end = max; // 在上次跳跃的范围之内，能在本次跳跃的最远距离
                step++;
            }
        }
        return step;
    }
}
