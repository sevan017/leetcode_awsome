package daily_question._2026._0630;

import java.util.ArrayList;

public class ParallelCourses3 {
    public static void main(String[] args) {
        System.out.println(minimumTime(3, new int[][]{{1, 3}, {2, 3}}, new int[]{3, 2, 5}));
    }

    public static int minimumTime(int n, int[][] relations, int[] time) {
        // 自己尝试一次
        // 建图：邻接表
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            // n门课之间的关系
            graph.add(new ArrayList<>());
        }

        // 建立图
        for (int[] r : relations) {
            // r[1] 必须在r[0]后完成，r[1]完成依赖于r[0]
            graph.get(r[1]).add(r[0]); // 有向图
            // 记录度
            indegree[r[0]]++;
        }
        int ans = 0; // 记录答案
        int l = 1, r = 1;
        int[] queue = new int[n + 1]; // 记录度为1的节点
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i;
            }
        }
        while (l < r) {
            // 当前
            int cur = queue[l++];
            ans = Math.max(ans, time[cur]); // 当前课程的时间
            // 遍历图
            for (int next : graph.get(cur)) {
                // next 下一门课
                time[next - 1] += time[cur]; // 将cur花费时间加入next
                // cur->next 被处理了
                if (--indegree[next] == 0) {
                    queue[r++] = next;
                }
            }
        }
        return ans;
    }
}
