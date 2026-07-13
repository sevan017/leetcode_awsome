package daily_question._2026._0713;

import java.util.ArrayList;
import java.util.PriorityQueue;

//https://leetcode.cn/problems/DFPeFJ/
public class Code05_VisitCityMinCost {
    public static void main(String[] args) {

        System.out.println(Solution.electricCarPlan(new int[][]{{1, 3, 3}, {3, 2, 1}, {2, 1, 3}, {0, 1, 4}, {3, 0, 5}}, 6, 1, 0, new int[]{2, 10, 4, 1}));
    }

    static class Solution {
        // 电动车总电量 cnt
        public static int electricCarPlan(int[][] paths, int cnt, int start, int end, int[] charge) {
            int n = charge.length;
            // 建图，无向图
            ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] path : paths) {
                graph.get(path[0]).add(new int[]{path[1], path[2]});
//                error ： 建图错误
//                graph.get(path[1]).add(new int[]{path[2], path[1]});
                graph.get(path[1]).add(new int[]{path[0], path[2]});
            }
            // n : 0 ~ n-1 不代表图上的点
            // (点，到达这个点的电量) 图上的点
            int[][] distance = new int[n][cnt + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= cnt; j++) {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
            distance[start][0] = 0;
            boolean[][] visited = new boolean[n][cnt + 1];
            // 0 : 当前点
            // 1 : 来到当前点的电量
            // 2 : 花费时间
            PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);
            // 起始位置 start 电量 0，花费0
            heap.add(new int[]{start, 0, 0});
            while (!heap.isEmpty()) {
                int[] record = heap.poll();
                int cur = record[0];
                int power = record[1];
                int cost = record[2];
                // 在power电量下到达了cur点，跳过
                if (visited[cur][power]) {
                    continue;
                }
                if (cur == end) {
                    // 到达目的地 剪枝
                    return cost;
                }
                visited[cur][power] = true;

                if (power < cnt) {
                    // 电车未满电，冲一格电量
                    if (!visited[cur][power + 1] && cost + charge[cur] < distance[cur][power + 1]) {
                        distance[cur][power + 1] = cost + charge[cur];
                        heap.add(new int[]{cur, power + 1, cost + charge[cur]});
                    }
                }
                // 去往下一个城市
                for (int[] edge : graph.get(cur)) {
                    int nextCity = edge[0];
                    int restPower = power - edge[1];
                    int nextCost = cost + edge[1];
                    // restPower >= 0 能达到下一个城市
                    if (restPower >= 0 && !visited[nextCity][restPower] && nextCost < distance[nextCity][restPower]) {
                        distance[nextCity][restPower] = nextCost;
                        heap.add(new int[]{nextCity, restPower, nextCost});
                    }
                }
            }
            return -1;
        }
    }
}
