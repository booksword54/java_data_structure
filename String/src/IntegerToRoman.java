public class IntegerToRoman {

    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] chars = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    // 整数转罗马数字
    public String intToRoman(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        int len = values.length;
        for (int i = 0; i < len; i++) {
            int value = values[i];
            while (num >= value) {
                num -= value;
                stringBuilder.append(chars[i]);
            }
            if (num == 0) {
                break;
            }
        }
        return stringBuilder.toString();
    }
}
