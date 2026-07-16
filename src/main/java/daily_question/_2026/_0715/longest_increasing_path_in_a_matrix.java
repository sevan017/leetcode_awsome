package daily_question._2026._0715;

// 矩阵中的最长递增路径
// 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度
// 对于每个单元格，你可以往上，下，左，右四个方向移动
// 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）
// 测试链接 : https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/
public class longest_increasing_path_in_a_matrix {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.longestIncreasingPath(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}));
        System.out.println(solution.longestIncreasingPath(new int[][]{{7, 7, 5}, {2, 4, 6}, {8, 2, 0}}));
    }

    static class Solution {
        public int longestIncreasingPath(int[][] matrix) {
            int ans = 0;
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] dp = new int[m][n];
            // 枚举每个位置进行dfs
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    ans = Math.max(ans, f(matrix, i, j, dp));
                }
            }
            return ans;
        }

        int f(int[][] matrix, int i, int j, int[][] dp) {
            if (dp[i][j] != 0) {
                return dp[i][j];
            }
//            每个方向获取的值需要比较一下，取最大的
            int next = 0;
            // 上
            if (i > 0 && matrix[i - 1][j] > matrix[i][j]) {
                next = Math.max(next, f(matrix, i - 1, j, dp));
            }
            // 下
            if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
                next = Math.max(next, f(matrix, i + 1, j, dp));
            }
            // 左
            if (j > 0 && matrix[i][j - 1] > matrix[i][j]) {
                next = Math.max(next, f(matrix, i, j - 1, dp));
            }
            // 右
//            j < matrix[0].length 错误。因为后面会寻址 j + 1, 如果j == matrix[0].length - 1。j + 1 就是 matrix[0].length 会出错
            if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
                next = Math.max(next, f(matrix, i, j + 1, dp));
            }
            dp[i][j] = next + 1;
            return next + 1;
        }
    }
}
