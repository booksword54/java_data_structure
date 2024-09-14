public class OneAway {
    // 字符串有三种编辑操作:插入一个英文字符、删除一个英文字符或者替换一个英文字符。
    // 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
    public boolean oneEditAway(String first, String second) {
        int n = first.length();
        int m = second.length();
        if (Math.abs(n - m) > 1) {
            return false;
        }
        if (n > m) {
            return oneEditAway(second, first);
        }
        int i = 0;
        int j = 0;
        int count = 0;
        while (i < n && j < m) {
            char c1 = first.charAt(i);
            char c2 = second.charAt(j);
            if (c1 == c2) { // 遍历位置的字符相等，直接跳弓
                i++;
                j++;
            } else { // 不相等，做替换或者删除处理
                if (n == m) { // 替换
                    i++;
                    j++;
                } else { // 删除
                    j++;
                }
                if (++count > 1) { // 替换或者删除处理的次数超过一
                    return false; // 做一次操作不能转换
                }
            }
        }
        return true;
    }
}
