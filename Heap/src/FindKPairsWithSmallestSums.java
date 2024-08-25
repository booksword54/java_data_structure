import java.util.*;

public class FindKPairsWithSmallestSums {
    // 查找和最小的 K 对数字
    // 给定两个以 非递减顺序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
    // 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
    // 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。

    // 优先队列 堆 pair表示取得元素下标组合
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, Comparator.comparingInt(pair -> (nums1[pair[0]] + nums2[pair[1]])));
        List<List<Integer>> res = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.add(new int[]{i, 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            res.add(list);
            if (idxPair[1] + 1 < n) {
                pq.add(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }
        return res;
    }
}
