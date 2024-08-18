import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    // 两数之和
    // 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
    // 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
    // 你可以按任意顺序返回答案。
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 出现了和为target的两个元素
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            // 记录当前元素的位置，便于以后匹配
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
