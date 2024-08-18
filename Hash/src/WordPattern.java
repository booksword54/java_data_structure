import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordPattern {
    // 单词规律
    // 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
    // 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 s 中的每个非空单词之间存在着双向连接的对应规律。
    public boolean wordPattern(String pattern, String s) {
        List<String> strs  = Arrays.asList(s.split(" "));
        int len1 = pattern.length();
        int len2 = strs.size();
        if (len1 != len2) {
            return false;
        }
        Map<String, Character> str2ch = new HashMap<>();
        Map<Character, String> ch2str = new HashMap<>();
        for (int i = 0; i < len1; i++) {
            char ch = pattern.charAt(i); // 格式占位符
            String str = strs.get(i); // 和占位符一一对应的单词
            // 字符串和占位符需要一一对应
            if (str2ch.containsKey(str) && str2ch.get(str) != ch) {
                return false;
            }
            if (ch2str.containsKey(ch) && !ch2str.get(ch).equals(str)) {
                return false;
            }
            // 设置匹配关系
            str2ch.put(str, ch);
            ch2str.put(ch, str);
        }
        return true; // 所有占位符和字符串完全匹配
    }

    // 使用Java特性 indexOf
    public boolean wordPattern2(String pattern, String s) {
        List<String> strs = Arrays.asList(s.split(" "));
        int len1 = pattern.length();
        int len2 = strs.size();
        if (len1 != len2) {
            return false;
        }
        for (int i = 0; i < len1; i++) {
            // 调用一次indexOf获取元素第一次出现的位置
            // 第一次出现位置相同的元素一一匹配
            // 第一次出现的位置不同(后来的是新位置，先来的是老位置)，证明之前有其他元素匹配过了，则不是匹配的元素
            if (pattern.indexOf(pattern.charAt(i)) != strs.indexOf(strs.get(i))) {
                return false;
            }
        }
        return true;
    }

}
