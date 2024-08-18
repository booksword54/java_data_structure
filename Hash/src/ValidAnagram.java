public class ValidAnagram {
    // 有效的字母异位词
    // 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
    // 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
    public boolean isAnagram(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        if (len1 != len2) {
            return false;
        }
        int[] count = new int[26]; // 单词出现的次数的差异
        // 先统计s中字符出现的次数
        for (int i = 0; i < len1; i++) {
            count[s.charAt(i) - 'a']++;
        }
        // t中的字符抵消s中的字符计数
        for (int i = 0; i < len2; i++) {
            if (count[t.charAt(i) - 'a'] == 0) { // 对应元素不存在，或者t中的该字符太多了
                return false;
            }
            count[t.charAt(i) - 'a']--;
        }
        return true; // 每个字符的数量都相等
    }

}
