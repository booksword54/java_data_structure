package array;

import java.util.*;

public class InsertDeleteGetRandom {
    // O(1) 时间插入、删除和获取随机元素
    // 实现RandomizedSet 类：
    // - RandomizedSet() 初始化 RandomizedSet 对象
    // - bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
    // - bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
    // - int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
    // 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
    class RandomizedSet {

        List<Integer> nums; // 存放元素
        Map<Integer, Integer> indices; // 存放元素的位置
        Random random;

        public RandomizedSet() {
            nums = new ArrayList<>();
            indices = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (indices.containsKey(val)) {
                return false;
            }
            int index = nums.size();
            nums.add(val);
            indices.put(val, index);
            return true;
        }

        public boolean remove(int val) {
            if (!indices.containsKey(val)) {
                return false;
            }
            int index = indices.get(val);
            int last = nums.get(nums.size() - 1);
            nums.set(index, last);
            indices.put(last, index);
            nums.remove(nums.size() - 1);
            indices.remove(val);
            return true;
        }

        public int getRandom() {
            int randomIdx = random.nextInt(nums.size());
            return nums.get(randomIdx);
        }
    }

}
