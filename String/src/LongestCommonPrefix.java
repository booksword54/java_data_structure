public class LongestCommonPrefix {
    // 最长公共前缀
    // 编写一个函数来查找字符串数组中的最长公共前缀。
    // 如果不存在公共前缀，返回空字符串 ""。

    // 横向扫描
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) { // 单词
            prefix = longestCommonPrefix(prefix, strs[i]); // 前缀和单词的最长前缀
            if (prefix.length() == 0) { // 若不存在前缀，提前终止
                break;
            }
        }
        return prefix;
    }

    private String longestCommonPrefix(String prefix, String str) {
        int length = Math.min(prefix.length(), str.length());
        int index = 0;
        while (index < length && prefix.charAt(index) == str.charAt(index)) {
            index++;
        }
        return prefix.substring(0, index);
    }

    // 纵向扫描
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) { // 前缀长度
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) { // 单词
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
