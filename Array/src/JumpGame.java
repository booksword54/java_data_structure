public class JumpGame {
    // 跳跃游戏
    // 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
    // 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
    // 贪心
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (i <= max) { // 还在范围内，继续取能跳到的最远距离
                max = Math.max(max, i + nums[i]); // 充当本次跳跃范围内，下一跳的最远距离
                if (max >= n - 1) {
                    return true; // 超出了目标
                }
            }
        }
        return false;
    }
}
