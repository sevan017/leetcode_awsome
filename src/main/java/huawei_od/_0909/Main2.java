package huawei_od._0909;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * <p>
 * 题目内容
 * 给定一个无向图，顶点编号从 1 到 n；从顶点 1 出发，进行深度优先搜索 (DFS)，
 * 当某个顶点有多个邻接点时，按照编号从小到大的顺序依次访问，输出遍历过程中访问顶点的顺序。1≤n≤100,
 * 0≤m≤100。若不连通，DFS 从顶点 1 出发无法遍历所有顶点，输出只包含可达顶点。
 * 输入保证没有自环如 (i,i)，即顶点到自身的边；同时输入保证不会有多条相同的边，如 (1,2) 出现两次。
 * 输入描述
 * <p>
 * 整数 n,m：表示顶点数和边数；
 * 二维数组 graph：每个元素有两个整数 u,v，表示 u 和 v 之间有一条无向边；
 */
// https://blog.csdn.net/qq_45776114/article/details/164765346
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] graph = new int[m][2];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            graph[i][0] = u;
            graph[i][1] = v;
        }

        ArrayList<Integer> ans = solve(n, m, graph);

        for (int i = 0; i < ans.size(); i++) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(ans.get(i));
        }
    }

    static ArrayList<Integer> solve(int n, int m, int[][] graph) {
        ArrayList<Integer> ans = new ArrayList<>();

        // 联接矩阵
        boolean[][] edges = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            int u = graph[i][0];
            int v = graph[i][1];

            edges[u][v] = true;
            edges[v][u] = true;
        }

        // 去重
        boolean[] vis = new boolean[n + 1];
        // 递归获取访问顺序
        dfs(1, vis, ans, edges);

        return ans;
    }

    // 递归访问
    static void dfs(int index, boolean[] vis, ArrayList<Integer> path, boolean[][] edges) {
        vis[index] = true;
        path.add(index);
        for (int i = 0; i < edges[index].length; i++) {
            // 不存在路径
            if (!edges[index][i]) {
                continue;
            }
            // 已经访问过
            if (vis[i]) {
                continue;
            }
            dfs(i, vis, path, edges);
        }
    }
}
