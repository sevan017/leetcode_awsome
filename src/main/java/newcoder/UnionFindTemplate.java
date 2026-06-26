package newcoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class UnionFindTemplate {
    // 静态常量
    static int MAXN = 1000001;
    static int[] father = new int[MAXN];
    static int[] size = new int[MAXN];
    static int[] stack = new int[MAXN];
    static int n; // 输入大小

    public static void main(String[] args) throws IOException {
        // 1、处理输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);

//        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            // 初始化
//            int n = (int) in.nval;
            n = (int) in.nval;
            build();
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                int opt = (int) in.nval;
                in.nextToken();
                int x = (int) in.nval;
                in.nextToken();
                int y = (int) in.nval;
                if (opt == 1) {
//                    out.println();
                    System.out.println(isSameSet(x, y) ? "Yes" : "No");
                } else if (opt == 2) {
                    union(x, y);
                }
            }
        }
//        out.flush();
//        out.close();
        br.close();
    }

    static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    static void union(int x, int y) {
        //   合并
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            if (size[fx] > size[fy]) {
                father[fx] = fy;
                size[fx] += size[fy];
            } else {
                father[fy] = fx;
                size[fy] += size[fx];
            }

        }
    }

    static int find(int i) {
        //
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    static void build() {
        for (int i = 1; i <= n; i++) {
            father[i] = i;
            size[i] = 1;
        }
    }
}