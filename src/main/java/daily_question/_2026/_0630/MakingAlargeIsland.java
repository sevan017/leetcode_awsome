package daily_question._2026._0630;

//making-a-large-island
//https://leetcode.cn/problems/making-a-large-island/
public class MakingAlargeIsland {
    public static void main(String[] args) {
        System.out.println(largestIsland(new int[][]{{1, 0}, {0, 1}}));
    }

    public static int largestIsland(int[][] grid) {
        // 洪水填充，dfs，将每个岛屿填充
        int n = grid.length;
        int m = grid[0].length;

        int id = 2; // 将到从2开始编号
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, n, m, i, j, id++);
                }
            }
        }
        // 岛屿数量 id - 2。接下分析将每个为0的位置修改为1后，看连接的岛屿总数 2 3
        int[] size = new int[id];
        // ans 记录最大岛屿的数量，因为可能输入的矩阵全为1，那么输入的就是最大岛屿数量
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] > 1) {
                    ++size[grid[i][j]]; // 记录每个编号岛屿的数量
                    ans = Math.max(ans, size[grid[i][j]]);
                }
            }
        }

        int up, down, left, right, merge = 0;
        boolean[] visited = new boolean[id]; // visited[i]表示 岛屿i是否被访问过
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    // 查看i , j 位置的上面一个岛屿是几号
                    up = i > 0 ? grid[i - 1][j] : 0;
                    down = i < n - 1 ? grid[i + 1][j] : 0;
                    left = j > 0 ? grid[i][j - 1] : 0;
                    right = j < m - 1 ? grid[i][j + 1] : 0;

                    // 先加上面的岛屿数量
                    visited[up] = true;
                    merge += size[up] + 1;

                    if (!visited[down]) {
                        merge += size[down];
                        visited[down] = true;
                    }
                    if (!visited[left]) {
                        merge += size[left];
                        visited[left] = true;
                    }
                    if (!visited[right]) {
                        merge += size[right];
                        visited[right] = true;
                    }

                    ans = Math.max(merge, ans);

                    // 将访问标记置为false
                    visited[up] = false;
                    visited[down] = false;
                    visited[left] = false;
                    visited[right] = false;
                }
            }
        }
        return ans;
    }

    static void dfs(int[][] grid, int n, int m, int i, int j, int id) {
        if (i < 0 || j < 0 || i == n || j == m || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = id;
        // 上下左右
        dfs(grid, n, m, i - 1, j, id);
        dfs(grid, n, m, i + 1, j, id);
        dfs(grid, n, m, i, j - 1, id);
        dfs(grid, n, m, i, j + 1, id);
    }
}
