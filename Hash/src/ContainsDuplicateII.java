import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainsDuplicateII {
    // 存在重复元素 II
    // 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，
    // 满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
    // 哈希表
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            // 出现了满足条件的两个位置
            if (map.containsKey(num) && i - map.get(num) <= k) {
                return true;
            }
            // 记录元素的位置，便于之后匹配条件
            map.put(num, i);
        }
        return false;
    }

    // 滑动窗口
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i > k) {
                window.remove(nums[i - k - 1]);
            }
            // 长度小于等于k的窗口内出现了相同的元素，满足条件
            if (!window.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}
