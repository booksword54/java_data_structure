package array;

import java.util.Arrays;

public class Candy {
    // 分发糖果
    // n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
    // 你需要按照以下要求，给这些孩子分发糖果：
    // - 每个孩子至少分配到 1 个糖果。
    // - 相邻两个孩子评分更高的孩子会获得更多的糖果。
    // 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。

    // 前缀数组 + 贪心算法
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                // 只比前面较小的人多一个
                candies[i] = candies[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                // 确保比两边较小的人都多，且多一个
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        int sum = 0;
        for (int candy : candies) {
            sum += candy;
        }
        // 最少的糖果数
        return sum;
    }
}
