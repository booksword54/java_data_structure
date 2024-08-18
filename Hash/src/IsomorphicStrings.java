import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {
    // 同构字符串
    // 给定两个字符串 s 和 t ，判断它们是否是同构的。
    // 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
    // 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，
    // 相同字符只能映射到同一个字符上，字符可以映射到自己本身。
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int len = s.length();
        // 字符在字符串中首次出现的位置
        Map<Character, Integer> sFirstIndex = new HashMap<>();
        Map<Character, Integer> tFirstIndex = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            // sc 和 tc 是一一对应的，在字符串中出现的位置相同，统计第一次出现的位置
            if (!sFirstIndex.containsKey(sc)) {
                sFirstIndex.put(sc, i);
            }
            if (!tFirstIndex.containsKey(tc)) {
                tFirstIndex.put(tc, i);
            }
            // 字符第一次出现的位置不相等，不是同构的
            if (sFirstIndex.get(sc) != tFirstIndex.get(tc)) {
                return false;
            }
        }
        return true; // 所有字符第一次出现的位置都相同，证明是同构的
    }
}
