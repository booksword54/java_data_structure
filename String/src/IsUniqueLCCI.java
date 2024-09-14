public class IsUniqueLCCI {
    // 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
    public boolean isUnique(String astr) {
        // 映射标记
        int mark = 0;
        for (int i = 0; i < astr.length(); i++) {
            // 判断每个字符在mark中出现的位置
            int moveBit = astr.charAt(i) - 'a';
            // 该字符是否出现
            if ((mark & (1 << moveBit)) != 0) {
                // 出现相同的字符
                return false;
            }
            // 将标记中字符映射位置的二进制数字设置成1
            mark = mark | (1 << moveBit);
        }
        return true;
    }
}
