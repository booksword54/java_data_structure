import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class BasicCalculator {
    // 基本计算器
    // 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
    // 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
    public int calculate(String s) {
        int res = 0, sign = 1, num = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0'); // 计算完整的操作数
            } else if (c == '+' || c == '-') {
                res += sign * num; // 加上本操作数带符号
                num = 0; // 下一个操作数
                if (c == '+') {
                    sign = 1; // 操作数是正数
                } else {
                    sign = -1; // 操作数是负数
                }
            } else if (c == '(') {
                stack.addLast(res); // 括号左边的操作数
                stack.addLast(sign); // 括号前的符号
                res = 0; // 括号内的结果
                sign = 1; // 括号内的符号
            } else if (c == ')') {
                // 括号内的结果计算完毕
                res += sign * num;
                num = 0; // 括号后的操作数
                res *= stack.removeLast(); // 括号前的符号
                res += stack.removeLast(); // 括号左边的操作数
            }
        }
        // 总体的结果计算完毕
        res += sign * num;
        return res;
    }
}
