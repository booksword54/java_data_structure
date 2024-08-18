public class RansomNote {
    // 赎金信
    // 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
    // 如果可以，返回 true ；否则返回 false 。
    // magazine 中的每个字符只能在 ransomNote 中使用一次。
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26]; // 统计26个单词出现的次数
        for (char c : magazine.toCharArray()) {
            count[c - 'a']++; // magazine字符统计
        }
        for (char c : ransomNote.toCharArray()) {
            if (count[c - 'a'] == 0) { // magazine中不存在，或者ransomNote的相同字符较多，magazine该字符数量不够
                return false;
            }
            count[c - 'a']--; // 抵消magazine的字符
        }
        return true;
    }
}
