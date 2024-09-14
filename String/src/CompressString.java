public class CompressString {
    // 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
    public String compressString(String S) {
        if (S.length() == 0) {
            return S;
        }
        StringBuilder ans = new StringBuilder();
        char ch = S.charAt(0);
        int count = 1;
        for (int i = 0; i < S.length(); i++) {
            if (ch == S.charAt(i)) {
                count++;
            } else {
                ans.append(ch);
                ans.append(count);
                ch = S.charAt(i);
                count = i;
            }
        }
        ans.append(ch);
        ans.append(count);
        return ans.length() < S.length() ? ans.toString() : S;
    }
}
