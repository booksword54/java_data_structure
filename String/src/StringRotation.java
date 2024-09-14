public class StringRotation {
    // 字符串轮转。
    // 给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
    public boolean isFlipedString(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m != n) {
            return false;
        }
        if (n == 0) {
            return true;
        }
        // 遍历旋转的位置
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            // 判断s2是否是s1旋转后的数组
            for (int j = 0; j < n; j++) {
                // 旋转后位置的元素不等于该位置的元素，遍历下一个旋转点
                if (s1.charAt((j + i) % n) != s2.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            // 所有位置都满足。s3是s1旋转后的结果
            if (flag) {
                return true;
            }
        }
        return false;
    }

    // 搜索字符串，两个相同的字符串拼接，一定会包含该字符串旋转后的结果
    public boolean isFlipedString2(String s1, String s2) {
        return s1.length() == s2.length() && (s1 + s1).contains(s2);
    }
}
