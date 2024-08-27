public class AddBinary {
    // 二进制求和
    // 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int len1 = a.length();
        int len2 = b.length();
        int len = Math.max(len1, len2);
        int carry = 0;
        for (int i = 0; i < len; i++) {
            carry += i < len1 ? (a.charAt(len1 - 1 - i) - '0') : 0;
            carry += i < len2 ? (b.charAt(len2 - 1 - i) - '0') : 0;
            // 计算每位的二进制和
            res.append((char) (carry % 2 + '0'));
            carry /= 2;
        }
        if (carry > 0) {
            res.append('1');
        }
        return res.reverse().toString();
    }
}
