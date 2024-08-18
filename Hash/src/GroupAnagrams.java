import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    // 字母异位词分组
    // 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
    // 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] count = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                count[str.charAt(i) - 'a']++;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (count[i] != 0) { // 表示单词内字符出现的情况: 出现的字母和次数
                    stringBuilder.append((char) ('a' + i));
                    stringBuilder.append(count[i]);
                }
            }
            // 字母异位词内单词出现的情况是相同的，使用同一个key
            String key = stringBuilder.toString();
            // 字母异位词构成的组合
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
}
