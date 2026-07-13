package daily_question._2026._0713;

import java.util.ArrayList;
import java.util.PriorityQueue;

//https://leetcode.cn/problems/network-delay-time/description/
public class NetworkTimes {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
    }

    static class Solution {
        // 动态建图　＋ 普通堆建图
        public int networkDelayTime(int[][] times, int n, int k) {
            // 建立图
            ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] time : times) {
                // 有向图 u->v 权重w
                graph.get(time[0]).add(new int[]{time[1], time[2]});
            }
            // 因为下标从1开始，舍弃0位置
            boolean[] visited = new boolean[n + 1];
            // 设置每个到开始点k的位置为最大值
            int[] distance = new int[n + 1];
//            for (int i = 1; i < n; i++) error 因为下标处理的问题，导致整个结果错误
            for (int i = 1; i <= n; i++) {
                distance[i] = Integer.MAX_VALUE;
            }
            // [0] 目标点，[1]权重
            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]); // 小根堆
            // k->k 权重0
            heap.offer(new int[]{k, 0});
            // k为出发点，距离自身距距离肯定为0
            distance[k] = 0;

            // 核心逻辑，前面都是准备工作
            while (!heap.isEmpty()) {
                // 弹出堆顶元素
                int[] time = heap.poll();
                int u = time[0];
                if (visited[u]) {
                    // 1、已经弹出过跳过
                    continue;
                }
                visited[u] = true;
                // 处理从u出发的边
                for (int[] edge : graph.get(u)) {
                    int v = edge[0], w = edge[1];
                    if (distance[u] + w < distance[v]) {
                        // 从u->v 权重小于当前v的权重 更新
                        distance[v] = distance[u] + w;
                        heap.offer(new int[]{v, distance[v]});
                    }
                }
            }
            int ans = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                if (distance[i] == Integer.MAX_VALUE) {
                    return -1; // 说明i位置不能从k出发到达
                }
                ans = Math.max(ans, distance[i]);
            }
            return ans;
        }
    }
}
