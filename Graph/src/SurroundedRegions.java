import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegions {
    // 被围绕的区域
    // 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' 组成，捕获 所有 被围绕的区域：
    // 连接：一个单元格与水平或垂直方向上相邻的单元格连接。
    // 区域：连接所有 'O' 的单元格来形成一个区域。
    // 围绕：如果您可以用 'X' 单元格 连接这个区域，并且区域中没有任何单元格位于 board 边缘，则该区域被 'X' 单元格围绕。
    // 通过将输入矩阵 board 中的所有 'O' 替换为 'X' 来 捕获被围绕的区域。

    int[][] dirs = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    // 方法一：深度优先搜索
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        // 与边界相连的 O 标记为 A，不与边界相连的 O 不改变，证明被堵着了，标记为 X
        for (int i = 0; i < m; i++) {
            dfs(i, 0, m, n, board);
            dfs(i, n - 1, m, n, board);
        }
        for (int j = 1; j < n - 1; j++) {
            dfs(0, j, m, n, board);
            dfs(m - 1, j, m, n, board);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'A') { // 与边界相连的 O
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') { // 被围绕的 O
                    board[i][j] = 'X';
                }
            }
        }
    }

    // 蔓延标记与边界连通的 O
    private void dfs(int i, int j, int m, int n, char[][] board) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') {
            return;
        }
        // 标记为 A
        board[i][j] = 'A';
        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            dfs(ni, nj, m, n, board);
        }
    }

    // 方法二：广度优先搜索
    public void solve2(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        // 与边界直接相连的 O 标记为 A
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                queue.add(new int[]{i, 0});
                board[i][0] = 'A';
            }
            if (board[i][n - 1] == 'O') {
                queue.add(new int[]{i, n - 1});
                board[i][n - 1] = 'A';
            }
        }
        for (int j = 1; j < n - 1; j++) {
            if (board[0][j] == 'O') {
                queue.add(new int[]{0, j});
                board[0][j] = 'A';
            }
            if (board[m - 1][j] == 'O') {
                queue.add(new int[]{m - 1, j});
                board[m - 1][j] = 'A';
            }
        }
        // 漫延标记与边界连通的 O
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && board[nx][ny] == 'O') {
                    queue.add(new int[]{nx, ny});
                    board[nx][ny] = 'A';
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
