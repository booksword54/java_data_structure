package matrix;

public class ValidSudoku {
    // 有效的数独
    // 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
    // 数字 1-9 在每一行只能出现一次。
    // 数字 1-9 在每一列只能出现一次。
    // 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9]; // 某一行是否出现该数字
        int[][] columns = new int[9][9]; // 某一列是否出现该数字
        int[][][] subboxes = new int[3][3][9]; // 某一3*3宫是否出现该数字
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    // 某一行、列、宫格已经存在该数字
                    if (rows[i][index] > 0 || columns[j][index] > 0 || subboxes[i / 3][j / 3][index] > 0) {
                        return false;
                    }
                    rows[i][index]++;
                    columns[j][index]++;
                    subboxes[i / 3][j / 3][index]++;
                }
            }
        }
        return true;
    }
}
