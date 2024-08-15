import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    // 罗马数字转整数
    Map<Character, Integer> map = new HashMap<>() {
        {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }
    };

    public int romanToInt(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int value = map.get(s.charAt(i));
            if (i < n - 1 && value < map.get(s.charAt(i + 1))) {
                // IV IX XL XC CD CM
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;
    }

}
