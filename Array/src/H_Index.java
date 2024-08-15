public class H_Index {
    // 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
    // 根据维基百科上 h 指数的定义：h 代表“高引用次数” ，一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，
    // 并且 至少 有 h 篇论文被引用次数大于等于 h 。如果 h 有多种可能的值，h 指数 是其中最大的那个。
    public int hIndex(int[] citations) {
        int left = 0;
        int right = citations.length;
        // 双指针定位h指数
        while (left < right) {
            int mid = left + (right - left) >> 1;
            int count = 0;
            for (int citation : citations) {
                if (citation >= mid) {
                    count++;
                }
            }
            if (count < mid) {
                // 范围偏大，缩减
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
