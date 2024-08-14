public class BestTimeToBuyAndSellStockII {
    // 买卖股票的最佳时机 II
    // 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
    // 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
    // 返回 你能获得的 最大 利润 。

    // 贪心
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len <= 1) {
            return 0;
        }
        int profit = 0;
        for (int i = 1; i < len; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                // 只买第二天赚钱的股票
                profit += diff;
            }
        }
        return profit;
    }

    // 动态规划
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int buy = -prices[0];
        int sell = 0;
        int preBuy = buy;
        int preSell = sell;
        for (int i = 1; i < n; i++) {
            // 之前买入的最大利润，之前卖出的最大利润 - 本次价格
            buy = Math.max(preBuy, preSell - prices[i]);
            // 之前卖出的最大利润，之前买入的最大利润 + 本次价格
            sell = Math.max(preSell, preBuy + prices[i]);
            preBuy = buy;
            preSell = sell;
        }
        return sell;
    }
}
