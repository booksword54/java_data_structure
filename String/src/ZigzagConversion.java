import java.util.ArrayList;
import java.util.List;

public class ZigzagConversion {
    // Z 字形变换
    // 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        int r = 0, direction = -1;
        for (char c : s.toCharArray()) {
            rows.get(r).append(c); // 按方向一行行地追加字符
            // 添加到第一行和最后一行的时候，变换方向，Z型的添加字符
            if (r == 0 || r == numRows - 1) {
                direction = -direction;
            }
            r += direction;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }
}
