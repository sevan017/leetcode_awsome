package luogu._0712;

// 双向BFS

import java.io.*;
import java.util.Arrays;

public class Code02_SnacksWaysBuyTickets {

    static int MAXN = 40;

    // 1 * 2^20方，刚好是最大结果的一半
    static int MAXM = 1 << 20;
    static long[] arr = new long[MAXN];
    // 左边一半
    static long[] lsum = new long[MAXM];
    // 右边一半
    static long[] rsum = new long[MAXM];

    static int n; // 比赛数量

    static long w; // 资产

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);

        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            w = (long) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (long) in.nval;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    static long compute() {
        // 0 ~ n / 2范围内的比赛选择
        int lsize = f(0, n >> 1, 0, w, lsum, 0);
        // n / 2 ~ n 剩下的比赛选择
        int rsize = f(n >> 1, n, 0, w, rsum, 0);
        Arrays.sort(lsum, 0, lsize);
        Arrays.sort(rsum, 0, rsize);
        long ans = 0;
        for (int i = lsize - 1, j = 0; i >= 0; i--) {
            // i固定情况下，尝试j
            while (j < rsize && lsum[i] + rsum[j] <= w) {
                j++;
            }
            ans += j;
        }
        return ans;
    }

    // [i ~ e) arr数组的范围内的比赛
    // s 所选的比赛的和
    // ans[i..e-1]选择的比赛后的答案
    // j ans数组的范围
    static int f(int i, int e, long s, long w, long[] ans, int j) {
        if (s > w) {
            // 选择的比赛超出预算
            return j;
        }
        // i == e 所有比赛都看了一遍
        if (i == e) {
            ans[j++] = s;
        } else {
            // 不选择arr[i]次比赛
            j = f(i + 1, e, s, w, ans, j);
            j = f(i + 1, e, s + arr[i], w, ans, j);
        }
        return j;
    }
}