package daily_question._2026._0704;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/minimum-score-of-a-path-between-two-cities/description/?envType=daily-question&envId=2026-07-04
public class minimum_score_of_a_path_between_two_cities {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.minScore(4, new int[][]{{1, 2, 9}, {2, 3, 6}, {2, 4, 5}, {1, 4, 7}}));
    }

    static class Solution {
        public int minScore(int n, int[][] roads) {
            // 有权无向图
            List<ArrayList<int[]>> graph = new ArrayList<ArrayList<int[]>>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<int[]>());
            }
            for (int[] road : roads) {
                // a->b w
                graph.get(road[0]).add(new int[]{road[1], road[2]});
                // b->a w
                graph.get(road[1]).add(new int[]{road[0], road[2]});
            }
            int ans = Integer.MAX_VALUE;
            // 遍历图 dfs
            ans = Math.min(ans, dfs(graph, 1, n, 0));
            return ans;
        }

        int dfs(List<ArrayList<int[]>> graph, int i, int n, int preI) {
            int weight = Integer.MAX_VALUE;
            for (int[] w : graph.get(i)) {
                if (w[0] == preI) {
                    continue;
                }
                if (w[0] == n) {
                    // 遍历到最后了
                    return w[1];
                }
                weight = w[1]; // 记录路径权重
                weight = Math.min(weight, dfs(graph, w[0], n, i));
            }
            return weight;
        }
    }
}
