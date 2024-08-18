package array;

public class ProductOfArrayExceptSelf {
    // 除自身以外数组的乘积
    // 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
    // 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
    // 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。

    // 前缀数组
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] left = new int[len]; // 左边元素乘积
        int[] right = new int[len]; // 右边元素乘积
        int[] res = new int[len]; // 结果数组
        left[0] = 1;
        for (int i = 1; i < len; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }
        right[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }
        for (int i = 0; i < len; i++) {
            res[i] = left[i] * right[i];
        }
        return res;
    }

    // 空间优化
    public int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        res[0] = 1;
        for (int i = 1; i < len; i++) {
            res[i] = nums[i - 1] * res[i - 1];
        }
        int right = 1;
        for (int i = len - 2; i >= 0; i--) {
            res[i] = res[i] * right;
            right *= nums[i];
        }
        return res;
    }
}
