package newcoder._0625;
//在字符串中找出连续最长的数字串

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 输入描述：
 * 输入一个长度为
 * 1
 * ≦
 * l
 * e
 * n
 * (
 * s
 * )
 * ≦
 * 200
 * 1≦len(s)≦200、由数字和小写字母混合构成的字符串
 * s
 * s。保证至少存在一个数字子串。
 * 输出描述：
 * 记最长的数字子串长度为
 * l
 * l，有
 * m
 * m 个长度为
 * l
 * l 的数字子串。在一行上先首尾相连的输出
 * m
 * m 个长度为
 * l
 * l 的数字子串（不使用空格分割），随后输出一个逗号，再输出
 * l
 * l。
 * https://www.nowcoder.com/exam/oj/ta?judgeStatus=1&page=1&pageSize=50&search=&tpId=37&type=37
 */
public class MaxLengthSubStr {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);

        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        // 数字子串最大长度
        int maxLength = 0;
        List<String> str = new ArrayList<>();
//        while (in.nextToken() != StreamTokenizer.TT_EOF) {
        // 原始字符串
        /**
         * StreamTokenizer 默认把字母、数字拆开识别，且纯字母 / 数字混合字符会被拆成数字、单词分开读取：
         * 字符串 11a22b33c 会被拆分识别为：
         * 数字 11 → 单词 a → 数字 22 → 单词 b → 数字 33 → 单词 c
         * 你调用 in.sval 拿到空，是因为当前读取到的标记是数字（TT_NUMBER），数字标记下 sval 为空字符串，数字存在 nval。
         */
//            String s = in.sval; 错误写法，对于字符串的读取
        String s;
        while ((s = br.readLine()) != null) {
            // 滑动窗口 l r 表示数字子串的开始位置和结束位置
            for (int l = 0, r = 0; r < s.length(); r++) {
                // int x = ;
                while (r < s.length() && s.charAt(r) - '0' >= 0 && s.charAt(r) - '0' <= 9) {
                    r++;
                }
                // 结算
                if (s.charAt(l) - '0' >= 0 && s.charAt(l) - '0' <= 9) {
                    maxLength = Math.max(maxLength, r - l);
                    str.add(s.substring(l, r));
                    l = r;
                }
                l++; // 就是r指向的是字母
            }

            StringBuilder sb = new StringBuilder();
            for (String s1 : str) {
                if (s1.length() == maxLength) {
                    sb.append(s1);
                }
            }
            out.println(sb.toString() + "," + maxLength);
        }

        out.flush();
        out.close();
        br.close();
    }
}
