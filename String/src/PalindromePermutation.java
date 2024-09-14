import java.util.HashMap;
import java.util.Map;

public class PalindromePermutation {
    // 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
    // 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
    // 回文串不一定是字典当中的单词。

    // 哈希表
    public boolean canPermutePalindrome(String s) {
        // 记录每一个字符出现的次数
        Map<Character, Integer> dic = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            dic.put(s.charAt(i), dic.getOrDefault(s.charAt(i), 0) + 1);
        }
        int count = 0; // 统计奇数个数的字符出现的个数
        for (Integer time : dic.values()) {
            if (time % 2 == 1) {
                // 奇数个数的字符出现的个数超过1个
                if (++count > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    // 位运算 ,字符在 a-z之间
    public boolean canPermutePalindrome2(String s) {
        int bit = 0;
        for (char c : s.toCharArray()) {
            // 元素出现的位置，如果出现奇数次则为1，为偶数次则为0
            bit ^= (1 << (c - 'a'));
        }
        // 判断只有一个位置出现为1
        return (bit & (bit - 1)) == 0;
    }
}
