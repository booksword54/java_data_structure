import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    // 岛屿数量
    // 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    // 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    // 此外，你可以假设该网格的四条边均被水包围。

    // 深度优先搜索
    int[][] dirs = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 岛屿边界，开始遍历染色
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j, m, n);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j, int m, int n) {
        grid[i][j] = '0'; // 染色，证明与遍历到的岛屿边界连通，属于一个小岛
        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] == '1') {
                dfs(grid, ni, nj, m, n);
            }
        }
    }

    // 广度优先遍历
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    grid[i][j] = '0';
                    Queue<Integer> border = new LinkedList<>();
                    border.add(i * cols + j);
                    while (!border.isEmpty()) {
                        int size = border.size();
                        for (int k = 0; k < size; k++) {
                            int land = border.remove();
                            int row = land / cols;
                            int col = land % cols;
                            if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                                border.add((row - 1) * cols + col);
                                grid[row - 1][col] = '0';
                            }
                            if (row + 1 < rows && grid[row + 1][col] == '1') {
                                border.add((row + 1) * cols + col);
                                grid[row + 1][col] = '0';
                            }
                            if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                                border.add(row * cols + (col - 1));
                                grid[row][col - 1] = '0';
                            }
                            if (col + 1 < cols && grid[row][col + 1] == '1') {
                                border.add(row * cols + (col + 1));
                                grid[row][col + 1] = '0';
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
}
