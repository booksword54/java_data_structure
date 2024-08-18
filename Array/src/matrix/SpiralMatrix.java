package matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    // 螺旋矩阵
    // 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 这里面的顺序是固定的: 右 下 左 上

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns; // 总共需要获取的元素个数
        int r = 0, c = 0;
        int dirIdx = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[r][c]);
            visited[r][c] = true;
            int nr = r + dirs[dirIdx][0];
            int nc = c + dirs[dirIdx][1];
            // 碰壁了，换个方向
            if (nr < 0 || nr >= rows || nc < 0 || nc >= columns || visited[nr][nc]) {
                dirIdx = (dirIdx + 1) % 4; // 右 下 左 上 循环
            }
            // 往前走
            r += dirs[dirIdx][0];
            c += dirs[dirIdx][1];
        }
        return order;
    }
}
