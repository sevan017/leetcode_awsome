package daily_question._2026._0530;

import java.util.Arrays;

public class MaximalRectangle {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximalRectangle(new char[][]{{'0', '1'}, {'1', '0'}}));
    }

    static class Solution {
        public static int MAXN = 201;
        public static int[] height = new int[MAXN];
        public static int[] stack = new int[MAXN];
        public static int r; // 栈顶

        public int maximalRectangle(char[][] matrix) {
            // 数组压缩技巧
            Arrays.fill(height, 0);
            int ans = 0;
            int n = matrix.length;
            int m = matrix[0].length;
            for (int i = 0; i < n; i++) {
                // 假设长方形必须以i行为底部
                for (int j = 0; j < m; j++) {
                    // 把求 0 ~ i - 1行的长方形压缩到一行
                    height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
//                    ans = Math.max(ans, mostMartixArea(m)); // 错位位置，压缩0 ~ i- 1还没结束
                }
                ans = Math.max(ans, mostMartixArea(m));
            }

            return ans;
        }

        int mostMartixArea(int m) {
            r = 0;
            long ans = 0;
            for (int i = 0; i < m; i++) {
                while (r > 0 && height[stack[r - 1]] >= height[i]) {
                    int cur = stack[--r]; // 栈顶元素位置
                    int left = r == 0 ? -1 : stack[r - 1];
                    ans = Math.max(ans, height[cur] * (i - left - 1));
                }
                // 压栈
                stack[r++] = i;
            }
            // 清算
            while (r > 0) {
                int cur = stack[--r];
                int left = r == 0 ? -1 : stack[r - 1];
                ans = Math.max(ans, height[cur] * (m - left - 1));
            }
            return (int) ans;
        }
    }
}
