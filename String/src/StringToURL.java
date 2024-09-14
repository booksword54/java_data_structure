public class StringToURL {
    // URL化。编写一种方法，将字符串中的空格全部替换为%20。
    // 假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。
    // （注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
    // 字符数组
    public String replaceSpaces(String S, int length) {
        // 先把字符串转换为字符数组
        char[] chars = S.toCharArray();
        int index = chars.length - 1;
        for (int i = length - 1; i >= 0; i--) {
            // 如果遇到空格就把它转化为 "%20"
            if (chars[i] == ' ') {
                chars[index--] = '0';
                chars[index--] = '2';
                chars[index--] = '%';
            } else {
                chars[index--] = chars[i];
            }
        }
        // 字符数组，起始下标，字符个数
        return new String(chars, index + 1, chars.length - (index + 1));
    }

    // StringBuilder
    public String replaceSpaces2(String S, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        // 逐渐遍历字符串
        for (int i = 0; i < length; i++) {
            // 如果不是空格加入到StringBuilder中，是空格就把"%20"加入到StringBuilder中
            char ch = S.charAt(i);
            if (ch == ' ') {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }
}
