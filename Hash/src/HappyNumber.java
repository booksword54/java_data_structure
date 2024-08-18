import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    // 快乐数
    // 编写一个算法来判断一个数 n 是不是快乐数。
    //「快乐数」 定义为：
    // - 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
    // - 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
    // - 如果这个过程 结果为 1，那么这个数就是快乐数。
    // 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。

    // 哈希集合检测循环
    public boolean isHappy(int n) {
        Set<Integer> calculated = new HashSet<>(); // 是否被计算过
        // 若计算出平方和是1，则是快乐数，退出循环
        // 若不是1，且被计算过，证明进入了无限循环，不是快乐数，退出循环
        while (n != 1 && !calculated.contains(n)) {
            calculated.add(n);
            n = getNext(n);
        }
        // 判断是哪种情况退出循环
        return n == 1;
    }

    // 计算平方和
    private int getNext(int num) {
        int sum = 0;
        while (num > 0) {
            int digit = num % 10;
            num /= 10;
            sum += digit * digit;
        }
        return sum;
    }

    // 快慢指针检测循环
    public boolean isHappy2(int n) {
        int slow = n;
        int fast = getNext(n);
        // 计算出结果为1，退出循环
        // 快指针追上慢指针，表示无限循环，退出循环。
        while (fast != 1 && fast != slow) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        // 判断是哪种情况退出循环
        return fast == 1;
    }
}
