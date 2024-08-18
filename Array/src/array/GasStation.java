package array;

public class GasStation {
    // 加油站
    // 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
    // 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
    // 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。

    // 贪心
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int sum = 0; // 当前油量
        int min = Integer.MAX_VALUE; // 最小油量
        int minIndex = -1; // 最小油量对应的加油站的下标
        for (int i = 0; i < len; i++) {
            sum += gas[i] - cost[i]; // 当前油量sum
            // 更新最小油量和对应的加油站下标
            if (sum < min && sum < 0) {
                min = sum;
                minIndex = i;
            }
        }
        if (sum < 0) {
            // 整个路线无法走完
            return -1;
        }
        return (minIndex + 1) % len; // 起始加油站位置
    }
}
