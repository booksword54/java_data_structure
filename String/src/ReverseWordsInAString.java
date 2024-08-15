import java.util.*;

public class ReverseWordsInAString {

    // 转字符串中的单词
    // 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
    // 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
    // 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
    // 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。

    // 双端队列
    public String reverseWords(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ') {
            left++;
        }
        while (left <= right && s.charAt(right) == ' ') {
            right--;
        }
        Deque<String> deque = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                // 将单词push到队列头部
                deque.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            left++;
        }
        // 收尾
        deque.offerFirst(word.toString());
        return String.join(" ", deque);
    }

    // Java语言特性
    public String reverseWords2(String s) {
        s = s.trim();
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
}
