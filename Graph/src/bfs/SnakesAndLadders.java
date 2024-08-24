package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
    // 蛇梯棋
    // 给你一个大小为 n x n 的整数矩阵 board ，方格按从 1 到 n^2 编号，编号遵循 转行交替方式 ，
    // 从左下角开始 （即，从 board[n - 1][0] 开始）每一行交替方向。

    // 玩家从棋盘上的方格 1 （总是在最后一行、第一列）开始出发。
    // 每一回合，玩家需要从当前方格 curr 开始出发，按下述要求前进：
    // - 选定目标方格 next ，目标方格的编号符合范围 [curr + 1, min(curr + 6, n^2)] 。
    // - - 该选择模拟了掷 六面体骰子 的情景，无论棋盘大小如何，玩家最多只能有 6 个目的地。
    // - 传送玩家：如果目标方格 next 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 next 。
    // - 当玩家到达编号 n^2 的方格时，游戏结束。

    // r 行 c 列的棋盘，按前述方法编号，棋盘格中可能存在 “蛇” 或 “梯子”；
    // 如果 board[r][c] != -1，那个蛇或梯子的目的地将会是 board[r][c]。
    // 编号为 1 和 n^2 的方格不是任何蛇或梯子的起点。

    // 注意，玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：
    // 就算目的地是另一条蛇或梯子的起点，玩家也 不能 继续移动。
    // - 举个例子，假设棋盘是 [[-1,4],[-1,3]] ，第一次移动，玩家的目标方格是 2 。
    // 那么这个玩家将会顺着梯子到达方格 3 ，但 不能 顺着方格 3 上的梯子前往方格 4 。
    // 返回达到编号为 n^2 的方格所需的最少移动次数，如果不可能，则返回 -1。

    // 广度优先搜索 寻找最短路径
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<int[]> queue = new LinkedList<>();
        // 第0步在位置1
        queue.add(new int[]{0, 1});
        // 层序遍历，走完骰子涉及到的所有位置，加入队列，访问下层...
        while (!queue.isEmpty()) {
            // 当前走的步以及所在的位置
            int[] step = queue.poll();
            // 走的步数，掷骰子决定
            for (int i = 1; i <= 6; i++) {
                // 走了几步的新位置索引
                int nextPosIdx = step[1] + i;
                // 位置越界
                if (nextPosIdx > n * n) {
                    break;
                }
                // 根据位置id获取位置的行列坐标
                int[] nextPos = getPosByIdx(nextPosIdx, n);
                // 如果 board[r][c] != -1，表示蛇或梯子，目的地将会是 board[r][c]。
                if (board[nextPos[0]][nextPos[1]] != -1) {
                    nextPosIdx = board[nextPos[0]][nextPos[1]]; // 蛇或梯子指向的位置
                }
                // 下一个位置是终点
                if (nextPosIdx == n * n) {
                    // 返回走动的步数，由于是层序遍历，首次遇到的终点，走了最短的路径
                    return step[0] + 1;
                }
                // 访问当前元素并添加到队列，下一步骤访问
                if (!visited[nextPosIdx]) {
                    visited[nextPosIdx] = true;
                    queue.add(new int[]{step[0] + 1, nextPosIdx});
                }
            }
        }
        // 不能走到终点
        return -1;
    }

    // 根据位置id获取位置的行列坐标
    private int[] getPosByIdx(int posIdx, int n) {
        int row = (posIdx - 1) / n;
        int col = (posIdx - 1) % n;
        // 奇数行，列数取反 Z字形走动
        if (row % 2 == 1) {
            col = n - 1 - col;
        }
        return new int[]{n - 1 - row, col};
    }
}
