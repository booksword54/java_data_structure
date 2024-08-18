package matrix;

public class GameOfLife {
    // 生命游戏
    // 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。
    // 每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。
    // 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
    // - 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
    // - 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
    // - 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
    // - 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
    // 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
    // 给你 m x n 网格面板 board 的当前状态，返回下一个状态。

    public void gameOfLife(int[][] board) {
        int[] neighbors = {0, 1, -1};
        int rows = board.length;
        int cols = board[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = 0; // 相邻位置存活细胞的数量
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) { // 查看相邻元素， (0,0)是自己
                            int r = row + neighbors[i];
                            int c = col + neighbors[j];
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (Math.abs(board[r][c]) == 1)) {
                                liveNeighbors += 1; // 相邻存活细胞加一
                            }
                        }
                    }
                }
                // 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
                // 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
                if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = -1; // 下局才会死亡，本局仍然存活，绝对值是1
                }
                // 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
                if ((board[row][col] == 1) && (liveNeighbors == 2 || liveNeighbors == 3)) {
                    board[row][col] = 1; // 下局存活，本局也存活，绝对值是1
                }
                // 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
                if (board[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 2; // 下局才会复活，，本局仍然死亡
                }
            }
        }
        // 更新细胞状态
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] > 0) { // 最新状态不是 -1 和 0
                    board[row][col] = 1;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }
}
