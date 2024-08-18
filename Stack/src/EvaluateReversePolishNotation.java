import java.util.Deque;
import java.util.LinkedList;

public class EvaluateReversePolishNotation {
    // 逆波兰表达式求值
    // 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
    // 请你计算该表达式。返回一个表示表达式值的整数。
    // 注意：
    // - 有效的算符为 '+'、'-'、'*' 和 '/' 。
    // - 每个操作数（运算对象）都可以是一个整数或者另一个表达式。
    // - 两个整数之间的除法总是 向零截断 。
    // - 表达式中不含除零运算。
    // - 输入是一个根据逆波兰表示法表示的算术表达式。
    // - 答案及所有中间计算结果可以用 32 位 整数表示
    public int evalRPN(String[] tokens) {
        Deque<Integer> numStack = new LinkedList<>(); // 存放操作数的栈
        int n = tokens.length;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            if (isNumber(token)) {
                numStack.push(Integer.parseInt(token));
            } else { // 是运算符，弹出两个操作数进行运算
                int num2 = numStack.pop();
                int num1 = numStack.pop();
                switch (token) { // 运算后入栈，参与之后的运算符操作
                    case "+" -> numStack.push(num1 + num2);
                    case "-" -> numStack.push(num1 - num2);
                    case "*" -> numStack.push(num1 * num2);
                    case "/" -> numStack.push(num1 / num2);
                    default -> {
                    }
                }
            }
        }
        return numStack.pop(); // 最后的数字就是所有运算后的结果
    }

    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }
}
