public class RotateMatrix {
    // 旋转矩阵
    // 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
    // 不占用额外内存空间能否做到？
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 第i层
        for (int i = 0; i < n / 2; i++) {
            // 第j个元素
            for (int j = 0; j < (n + 1) / 2; j++) { // 奇数的情况，不遗漏中间的元素
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i]; // matrix[n - y - 1][x]，下面都是这个规律
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = tmp;
            }
        }
    }
}
