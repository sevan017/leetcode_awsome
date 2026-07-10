package daily_question._2026._0710;

import java.io.*;
import java.util.Arrays;

// 繁忙的都市
// 一个非常繁忙的大都市，城市中的道路十分的拥挤，于是市长决定对其中的道路进行改造
// 城市的道路是这样分布的：城市中有n个交叉路口，有些交叉路口之间有道路相连
// 两个交叉路口之间最多有一条道路相连接，这些道路是双向的
// 且把所有的交叉路口直接或间接的连接起来了
// 每条道路都有一个分值，分值越小表示这个道路越繁忙，越需要进行改造
// 但是市政府的资金有限，市长希望进行改造的道路越少越好，于是他提出下面的要求：
// 1. 改造的那些道路能够把所有的交叉路口直接或间接的连通起来
// 2. 在满足要求1的情况下，改造的道路尽量少
// 3. 在满足要求1、2的情况下，改造的那些道路中分值最大的道路分值尽量小
// 作为市规划局的你，应当作出最佳的决策，选择哪些道路应当被修建
// 返回选出了几条道路 以及 分值最大的那条道路的分值是多少
// 测试链接 : https://www.luogu.com.cn/problem/P2330
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以直接通过

public class BusyCities {

    static int MAXN = 301;
    static int MAXM = 8001;
    // 并查集
    static int[] father = new int[MAXN];
    // 边的数量
//    static int[][] edges = new int[MAXN][3]; 手比脑子快，模板代码敲多了，敲错了
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

            // 边按分数排序。排序规则
//            Arrays.sort(edges, 0, m); 敲多了，少了比较的规则 (a, b)->a[2] - b[2]
            Arrays.sort(edges, 0, m, (a, b) -> a[2] - b[2]);
            build(n);
            int ans = 0, edgeCount = 0;
            for (int i = 0; i < m; i++) {
                if (union(edges[i][0], edges[i][1])) {
                    // 更新ans
                    ans = Math.max(ans, edges[i][2]);
                    edgeCount++;
                }
                if (edgeCount == n - 1) {
                    break;
                }
            }
            out.println(n - 1 + " " + ans);
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

    // 合并元素
    static boolean union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
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
