import java.util.Arrays;

public class CheckPermutation {
    // 给定两个由小写字母组成的字符串 s1 和 s2，请编写一个程序，
    // 确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
    // 排序法
    public boolean CheckPermutation(String s1, String s2) {
        if (s2.length() != s1.length()) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    // 哈希表
    public boolean CheckPermutation2(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] table = new int[128];
        for (int i = 0; i < s1.length(); i++) {
            table[s1.charAt(i)]++;
        }
        for (int i = 0; i < s2.length(); i++) {
            table[s2.charAt(i)]--;
            if (table[s2.charAt(i)] < 0) {
                return false;
            }
        }
        return true;
    }
}
