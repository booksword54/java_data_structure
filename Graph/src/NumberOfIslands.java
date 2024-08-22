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
                // 找到岛屿边界，深度遍历连通所有岛屿节点并染色
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j, m, n);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j, int m, int n) {
        grid[i][j] = '0'; // 染色，说明与遍历到的岛屿边界连通，属于一个小岛
        // 四个方向"蔓"延所有岛屿节点并染色
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
                // 找到岛屿边界，广度遍历连通所有岛屿节点并染色
                if (grid[i][j] == '1') {
                    res++;
                    bfs(grid, i, j, rows, cols);
                }
            }
        }
        return res;
    }

    private void bfs(char[][] grid, int i, int j, int rows, int cols) {
        Queue<Integer> border = new LinkedList<>();
        border.add(i * cols + j); // 岛屿位置id
        grid[i][j] = '0';
        while (!border.isEmpty()) {
            int land = border.remove();
            int r = land / cols;
            int c = land % cols;
            // 四个方向"漫"延所有连通岛屿节点
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == '1') {
                    border.add(nr * cols + nc);
                    grid[nr][nc] = 'O';
                }
            }
        }
    }
}
