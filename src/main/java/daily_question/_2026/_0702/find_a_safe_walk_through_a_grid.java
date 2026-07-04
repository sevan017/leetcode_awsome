package daily_question._2026._0702;

import java.util.ArrayList;
import java.util.List;

public class find_a_safe_walk_through_a_grid {
    public static void main(String[] args) {
//        0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0
        List<List<Integer>> grid = new ArrayList<>();
        ArrayList<Integer> row = new ArrayList<>();
        row.add(0);
        row.add(1);
        row.add(0);
        row.add(0);
        row.add(0);
        grid.add(row);

        ArrayList<Integer> row1 = new ArrayList<>();
        row1.add(0);
        row1.add(1);
        row1.add(0);
        row1.add(1);
        row1.add(0);
        grid.add(row1);

        ArrayList<Integer> row2 = new ArrayList<>();
        row2.add(0);
        row2.add(0);
        row2.add(0);
        row2.add(1);
        row2.add(0);
        grid.add(row2);

        findSafeWalk(grid, 1);
    }

    public static boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        boolean[] visited = new boolean[m * n];
        return bfs(grid, 0, 0, m, n, health, visited);
    }

    static boolean bfs(List<List<Integer>> grid, int i, int j, int m, int n, int health, boolean[] visited) {
        if (health <= 0 || i < 0 || j < 0 || i == m || j == n) {
            return false;
        }
        if (health > 0 && i == m - 1 && j == n - 1) {
            return true;
        }
        int value = grid.get(i).get(j);
        visited[i * n + j] = true;
        // 上下左右
        boolean up = false, down = false, left = false, right = false;
//        if (i > 0 || ) {
        up = i > 0 && !visited[(i - 1) * n + j] && bfs(grid, i - 1, j, m, n, health - value - grid.get(i - 1).get(j), visited);
        down = i < m - 1 && !visited[(i + 1) * n + j] && bfs(grid, i + 1, j, m, n, health - value - grid.get(i + 1).get(j), visited);
//        }
//        if (j > 0 || j < n - 1) {
        left = j > 0 && !visited[i * n + j - 1] && bfs(grid, i, j - 1, m, n, health - value - grid.get(i).get(j - 1), visited);
        right = j < n - 1 && !visited[i * n + j + 1] && bfs(grid, i, j + 1, m, n, health - value - grid.get(i).get(j + 1), visited);
//        }
        visited[i * n + j] = false;
        return up || down || left || right;
    }
}
