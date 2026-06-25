package huaweiod._0621;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://blog.csdn.net/qq_45776114/article/details/162208794
public class LogStatic {
    public static void main(String[] args) throws IOException {
//        1、处理输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 读取m, n
        String[] first = br.readLine().trim().split("\\s+");
        int n = Integer.valueOf(first[0]);
        int m = Integer.valueOf(first[1]);

        // 读取n行日志
        List<String> logs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            logs.add(br.readLine().toLowerCase());
        }
//        读取关键字
        List<String> keyWords = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            keyWords.add(br.readLine().toLowerCase());
        }
//        2、计算结果
        List<Integer> ans = compute(n, m, logs, keyWords);

//        3、输出结果
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
//        关闭链接
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ans.size(); i++) {
            str.append(ans.get(i));
            if (i != ans.size() - 1) {
                str.append(",");
            }
        }
        out.println(str);
        out.flush();
        out.close();
        br.close();
    }

    private static List<Integer> compute(int n, int m, List<String> logs, List<String> keyWords) {
//        1、将关键字存入map
        int[] keyWordsCount = new int[m];
        Map<String, Integer> keyWordsMap = new HashMap<>();
        for (int i = 0; i < keyWords.size(); i++) {
            keyWordsMap.put(keyWords.get(i), i);
        }

        boolean[][] isOccur = new boolean[n][m];

//        统计每个关键字出现的次数
        for (int i = 0; i < logs.size(); i++) {
//            logs.get(i) 将日志拆分，拆分后遍历，遍历时统计关键字次数，同时统计每条日志不同关键字出现的关联性
            List<String> words = tokenize(logs.get(i));
            for (String word : words) {
                if (keyWordsMap.containsKey(word)) {
//                    记录关键字出现次数
                    int pos = keyWordsMap.get(word);
                    keyWordsCount[pos]++;
//                  同时记录这个words出现了哪些关键字以及 分别出现的次数
                    isOccur[i][pos] = true; // 第i条日志出现了 第pos个关键字
                }
            }
        }

//        整理答案 1、每个关键字按顺序出现的次数
        List<Integer> ans = new ArrayList<>();
        for (int value : keyWordsCount) {
            ans.add(value);
        }
//      统计关键字的关键字的关联组合索引 n条日志，m个关键字。关键字不重复
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                // 查看i和j位置的两个关键字是否出现在多条日志
                // count 关联索引出现的次数
                int count = 0;
                for (int k = 0; k < n; k++) {
                    // k条日志
                    if (!isOccur[k][i] || !isOccur[k][j]) {
                        // 表示在第k条日志，i或j位置的关键字在日志中不存在，说明不能构成关联索引，后续步骤不继续
                        continue;
                    }
                    count++; // 能构成关联索引
                    if (count >= 2) {
                        // i j 位置的关键字在超过2条日志中出现了， 能构成关联索引。跳出日志遍历训练，寻找i 、j + 1
                        break;
                    }
                }
                // 判断是否更新关联索引。
                // i j 天然有序，都多余担心排序。其实发现不能过度担心
                if (count >= 2) {
                    ans.add(i);
                    ans.add(j);
                }
            }
        }
        return ans;
    }

    static List<String> tokenize(String log) {
        List<String> words = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        for (char c : log.toCharArray()) {
            if (isDelim(c)) { // 是分隔符
                if (cur.length() > 0) {
                    words.add(cur.toString());
                    cur.setLength(0); // 清空
                }
            } else {
                cur.append(c);
            }
        }
//        可能存在最后一个分词
        if (cur.length() > 0) {
            words.add(cur.toString());
        }
        return words;
    }

    static boolean isDelim(char c) {
        return Character.isWhitespace(c)
                || c == ','
                || c == '.'
                || c == '!'
                || c == '?'
                || c == ';'
                || c == ':';
    }
}
