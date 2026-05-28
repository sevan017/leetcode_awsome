package newcoder._0527;

import java.io.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class _0527 {
    static int MAXN = 1001;
    static int[][] arr = new int[MAXN][MAXN];
    static int N, M, q;
    // static int[][] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);

        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        //
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            N = (int) in.nval;
            in.nextToken();
            M = (int) in.nval;
            in.nextToken();
            q = (int) in.nval;

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    in.nextToken();
//                    arr[i][j] = (int) in.nval;
//                    你用同一个数组既做差分修改，又做前缀和计算。
//✅ 正确做法：必须用独立的差分数组，不能和原数组合并。
                    set(i, j, i, j, (int) in.nval);
                }
            }
            // 处理sum
            // sum = new int[N + 2][M + 2];
            // build();
//            print();
            for (int i = 0; i < q; i++) {
                in.nextToken();
                int a = (int) in.nval;
                in.nextToken();
                int b = (int) in.nval;
                in.nextToken();
                int c = (int) in.nval;
                in.nextToken();
                int d = (int) in.nval;
                in.nextToken();
                int v = (int) in.nval;

                // set
                set(a, b, c, d, v);
                print();
            }
            // 前缀和
            sum();
//            StringBuilder builder = new StringBuilder();
//            for(int i = 1; i <= N; i++){
//                builder.setLength(0);
//                for(int j = 1; j <= M; j++){
//                    builder.append(j != M ? arr[i][j] + " " : arr[i][j]);
//                }
//                out.println(builder.toString());
//            }
            clear();
//            out.flush();
            print();
        }

        out.close();
        br.close();
    }

    static void clear() {
        for (int i = 1; i <= N + 1; i++) {
            for (int j = 1; j <= M + 1; j++) {
                arr[i][j] = 0;
            }
        }
    }

    static void sum() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                //  上 + 左 -左上 + 自己
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1] + arr[i][j];
            }
        }
    }

    static void set(int a, int b, int c, int d, int v) {
        arr[a][b] += v;
        arr[a][d + 1] -= v;
        arr[c + 1][b] -= v;
        arr[c + 1][d + 1] += v;
    }

    static void print() {
        System.out.println("====start====");
        for (int i = 0; i <= N + 1; i++) {
            for (int j = 0; j <= M + 1; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=============================\n");
    }

    // static void build(){
    //     for(int i = 1; i <= N; i++){
    //        for(int j = 1; j <= M; j++){
    //           arr[i][j] = arr[i][j];
    //        }
    //     }
    // }
}