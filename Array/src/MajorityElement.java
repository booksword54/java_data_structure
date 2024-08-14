public class MajorityElement {
    // 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
    // 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
    public int majorityElement(int[] nums) {
        int cnt = 0;
        int res = 0;
        for (int num : nums) {
            // 如果超出半数的数一定存在，就是最后一次存活下来的数
            // 其他数即使抢占到了res，也会被超出半数的数抢占
            // 而超出半数的数，不会被其余的数抹除掉，最终抢占到res
            if (cnt == 0) {
                res = num;
            }
            if (num != res) {
                cnt--;
            } else {
                cnt++;
            }
        }
        return res;
    }
}
