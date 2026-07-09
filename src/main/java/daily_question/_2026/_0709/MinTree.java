package daily_question._2026._0709;


import java.io.*;
import java.util.Arrays;

// Kruskal算法模版（洛谷）
// 静态空间实现
// 测试链接 : https://www.luogu.com.cn/problem/P3366
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以直接通过
public class MinTree {
    static int MAXN = 5001;
    static int MAXM = 200001;
    // 并查集
    static int[] father = new int[MAXN];
    static int[][] edges = new int[MAXM][3];
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                edges[i][0] = (int) in.nval;
                in.nextToken();
                edges[i][1] = (int) in.nval;
                in.nextToken();
                edges[i][2] = (int) in.nval;
            }
            // 按权重排序
            Arrays.sort(edges, 0, m, (a, b) -> a[2] - b[2]);
            build(n);
            // 选择边建立最小生成树
            int ans = 0, edgeCount = 0;
            for (int i = 0; i < m; i++) {
                if (union(edges[i][0], edges[i][1])) {
                    ans += edges[i][2];
                    edgeCount++;
                }
            }
            out.println(edgeCount == n - 1 ? ans : "orz");
        }
        out.flush();
        out.close();
        br.close();
    }

    static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    static boolean union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
//           fx = father[fy] 导致洛谷没通过，模板代码不应该出错的
            father[fy] = fx;
            return true;
        }
        return false;
    }

    static void build(int n) {
        for (int i = 1; i <= n; i++) {
            father[i] = i;
        }
    }
}
