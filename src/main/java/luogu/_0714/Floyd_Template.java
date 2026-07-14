package luogu._0714;

import java.io.*;

//https://www.luogu.com.cn/problem/P2910#ide
//弗洛伊德算法模板题目
public class Floyd_Template {

    // 岛屿最大数量
    static int MAXN = 101;
    // 边的最多数量
    static int MAXM = 10001;
    // 需要走的路径
    static int[] path = new int[MAXM];
    // 距离
    static int[][] distance = new int[MAXN][MAXN];
    // n岛屿数，m边的数量, ans 答案
    static int n, m, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);

        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;

            // 读入走的路线
            for (int i = 0; i < m; i++) {
                in.nextToken();
//                path[i] = (int) in.nval; error 修正位置
                path[i] = (int) in.nval - 1;
            }
            // 读入图
            build();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    in.nextToken();
                    distance[i][j] = (int) in.nval;
                }
            }
            // floyd算法，计算各个点之间的最短路径
            floyd();
            ans = 0;
            for (int i = 1; i < m; i++) {
//                ans += distance[path[i - 1]][path[i]]; 因为path[i] 岛屿的数量是从1 开始，但distance距离图下标是从0开始的。error
                ans += distance[path[i - 1]][path[i]];
            }
            out.println(ans);
        }
        out.flush();
        out.close();
        br.close();
    }

    static void floyd() {
        // 三层for循环 必须先遍历桥接点
        for (int bridge = 0; bridge < n; bridge++) {
            // i->j 和i -> bridge -> j 进行比较，更小就更新
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distance[i][bridge] != Integer.MAX_VALUE && distance[bridge][j] != Integer.MAX_VALUE && distance[i][j] > distance[i][bridge] + distance[bridge][j]) {
                        distance[i][j] = distance[i][bridge] + distance[bridge][j];
                    }
                }
            }
        }

    }

    static void build() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
    }
}






