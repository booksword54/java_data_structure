import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class ValidParentheses {
    // 有效的括号
    // 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
    // 有效字符串需满足：
    // - 左括号必须用相同类型的右括号闭合。
    // - 左括号必须以正确的顺序闭合。
    // - 每个右括号都有一个对应的相同类型的左括号。

    Map<Character, Character> pairs = new HashMap<>() {{
        put(')', '(');
        put(']', '[');
        put('}', '{');
    }};

    public boolean isValid(String s) {
        int len = s.length();
        if (len % 2 == 1) { // 长度不为偶数，一定有不成对的括号
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            // 是右括号，尝试匹配堆顶元素，释放左括号
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    // 匹配栈顶元素失败，为空或者不是对应的左括号
                    return false;
                }
                // 左括号被匹配，出栈
                stack.pop();
            } else {
                // 左括号入栈，等待右括号消除
                stack.push(ch);
            }
        }
        return stack.isEmpty(); // 有剩余的左括号没有被匹配
    }
}
