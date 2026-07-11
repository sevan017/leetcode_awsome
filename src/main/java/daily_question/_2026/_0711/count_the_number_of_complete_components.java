package daily_question._2026._0711;

//https://leetcode.cn/problems/count-the-number-of-complete-components/?envType=daily-question&envId=2026-07-11
public class count_the_number_of_complete_components {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.countCompleteComponents(4, new int[][]{{1,0},{2,0},{2,1},{3,0}}));
    }

    static class Solution {
        // 0 开始
        static int MAXN = 50;
        static int[] father = new int[MAXN];

        public int countCompleteComponents(int n, int[][] edges) {
            // 构建并查集
            build(n);
            // m条边
            int m = edges.length;
            for (int i = 0; i < m; i++) {
                // 将边的2个几点合并
                union(edges[i][0], edges[i][1]);
            }

            // 统计每个集合的边的数量
            int[] edgeCount = new int[n];
            // 统计每个集合节点的数量
            int[] nodeCount = new int[n];
            for (int i = 0; i < n; i++) {
                nodeCount[find(i)]++;
            }
            for (int[] edge : edges) {
                edgeCount[find(edge[0])]++;
            }

            int ans = 0;
            for (int i = 0; i < n; i++) {
                if (find(i) == i && edgeCount[i] == nodeCount[i] * (nodeCount[i] - 1) / 2) {
                    ans++;
                }
            }

            return ans;
        }

        static void build(int n) {
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }

        static int find(int i) {
            if (i != father[i]) {
                father[i] = find(father[i]);
            }
            return father[i];
        }

        static void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                father[fx] = fy;
            }
        }
    }
}
