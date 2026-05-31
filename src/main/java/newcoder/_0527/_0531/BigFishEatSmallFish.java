package newcoder._0527._0531;

// 大鱼吃小鱼

import java.io.*;

/**
 * 描述
 * 小明最近喜欢上了俄罗斯套娃、大鱼吃小鱼这些大的包住小的类型的游戏。
 * <p>
 * 于是小明爸爸给小明做了一个特别版的大鱼吃小鱼游戏，他希望通过这个游戏
 * <p>
 * 能够近一步提高牛牛的智商。
 * <p>
 * 游戏规则如下：
 * <p>
 * 现在有N条鱼，每条鱼的体积为Ai，从左到右排成一排。
 * <p>
 * A数组是一个排列。
 * <p>
 * 小明每轮可以执行一次大鱼吃小鱼的操作
 * <p>
 * 一次大鱼吃小鱼的操作：对于每条鱼，它在每一次操作时会吃掉右边比自己小的第一条鱼
 * <p>
 * 值得注意的时，在一次操作中，每条鱼吃比自己小的鱼的时候是同时发生的。
 * <p>
 * 举一个例子，假设现在有三条鱼,体积为分别[5，4，3]，5吃4，4吃3，一次操作后就剩下[5]一条鱼。
 * <p>
 * 爸爸问小明，你知道要多少次操作，鱼的数量就不会变了嘛？
 * <p>
 * 输入描述：
 * 给定N；
 * <p>
 * 给定A数组
 * <p>
 * １＜＝N＜＝１０＾５
 * <p>
 * １＜＝Ai＜＝Ｎ
 * <p>
 * 输出描述：
 * 一行, 正整数, 表示要多少次操作，鱼的数量就不会变了。
 * https://www.nowcoder.com/practice/77199defc4b74b24b8ebf6244e1793de
 */
public class BigFishEatSmallFish {


    static int MAXN = 100001;
    static int[] arr = new int[MAXN];
    static int[][] stack = new int[MAXN][2]; // stack[i][0] 鱼的体重， stack[i][0]鱼吃掉后面鱼的轮数
    static int r, n; // r栈顶，n鱼的数量

    public static void main(String[] args) throws IOException {
        // 思路：单调栈，从后往前遍历，小压大。一旦出现比栈顶大的，
        // 当前元素吃掉栈顶元素并携带相应信息继续往前遍历
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);

        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) { // 注意 while 处理多个 case
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }

            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    static int compute() {
        int ans = 0;
        r = 0;
        for (int i = n - 1, turns = 0; i >= 0; i--) {
            // 从最后一条🐟开始处理
            turns = 0;
//            if (r > 0 && arr[i] > stack[r - 1][0]) {  // error: 错误1 while 写成了if
            while (r > 0 && arr[i] > stack[r - 1][0]) {
                int[] cur = stack[--r]; // 栈顶元素
                // 当前鱼需要吃掉栈顶元素
//                turns += cur[1] + 1; // error: 错误2 判断当前的轮数的方式
                turns = Math.max(turns + 1, cur[1]);
            }
            // 压栈
            stack[r][0] = arr[i];
            stack[r++][1] = turns;

            ans = Math.max(turns, ans);
        }

        return ans;
    }


}
