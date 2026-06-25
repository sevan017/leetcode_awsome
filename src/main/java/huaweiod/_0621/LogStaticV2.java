package huaweiod._0621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://blog.csdn.net/qq_45776114/article/details/162208794
public class LogStaticV2 {
    public static void main(String[] args) throws IOException {
//        模拟：核心是模拟吗？
        // 对于牛客编程题 核心要素分步 1、处理好输入  2、大思路分析：对于本题来说，求每个关键字的出现次数是比较简单的 2.1 每个关键字出现次数
        // 2.2 是否存在多个关键字在同一条日志出现，而且要求在2条以上日志出现
        // 3、题目中的其他信息：边界 大小写不敏感 排序等。比如排序是可以通过在计算的时候按照顺序计算处理，但在第一次时我将其当作了拦路虎，还是练的不够多的原因
        // 4、根据数据量规模猜测解题方法：10^3 大小的规模，1ms 时10^7 所以题目可以用多重for也能通过

        // 1、处理输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().trim().split(" ");
        int n = Integer.valueOf(first[0]);
        int m = Integer.valueOf(first[1]);
        List<String> logs = new ArrayList<>();
        List<String> keyWords = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            logs.add(br.readLine());
        }
        for (int i = 0; i < m; i++) {
            // 关键字提前转为小写
            keyWords.add(br.readLine().toLowerCase());
        }

        // 2、按照题目要求处理
        List<Integer> ans = compute(n, m, logs, keyWords);

        // 3、输出
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ans.size(); i++) {
            str.append(ans.get(i));
            if (i != ans.size() - 1) {
                str.append(",");
            }
        }
        System.out.println(str.toString());
    }

    static List<Integer> compute(int n, int m, List<String> logs, List<String> keyWords) {
        // 需要完成的内容：计算每个关键字出现次数，还需要有顺序，用一个和关键字数组一样长度的数组，能记录，还能保留顺序
        Map<String, Integer> keyWordsIndex = new HashMap<>();
        int[] keyWordsCount = new int[m];
        // 是否存在关联索引关键字 isOccur[i][j] = true 表示第i条日志中第j个关键字出现了。用于后续遍历关联索引
        boolean[][] isOccur = new boolean[n][m];

        for (int i = 0; i < m; i++) {
            keyWordsIndex.put(keyWords.get(i), i);
        }

        // 计算每个关键字在日志中数量。因为数据量小，双层for 执行次数也到不了10^7
        for (int i = 0; i < n; i++) {
            // log 每条日志有多个单词，需要拆分
            List<String> words = tokenize(logs.get(i));
            for (String word : words) {
                // 转小写后再判断
                word = word.toLowerCase();
                if (keyWordsIndex.containsKey(word)) {
                    // 说明关键字word 出现在了 第i条日志，首先记录关键字的数量
                    int pos = keyWordsIndex.get(word);
                    keyWordsCount[pos]++;
                    // 记录第i条日志中出现了第pos个关键字
                    isOccur[i][pos] = true;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        // 先记录关键字数量
        for (int count : keyWordsCount) {
            ans.add(count);
        }


        // 分析是否存在关联索引：假设选第i关键字和 i + 1关键字分别去判断n条日志中是否同时出现
        // 关键顺序：  先选关键字，再判断
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                int count = 0;
                // 判断i 和j 两个关键字是否在同一条日志中出现，遍历所有日志
                for (int k = 0; k < n; k++) {
                    if (!isOccur[k][i] || !isOccur[k][j]) {
                        // i 或j 不存在与第k条日志，不能构成关键索引
                        continue;
                    }
                    // 否则就能构成关联索引，需要计数，不同的关联索引都需要统计
                    count++;
                    if (count >= 2) {
                        break;
                    }
                }
                if (count >= 2) {
                    ans.add(i);
                    ans.add(j);
                }
            }
        }
        return ans;
    }

    // 通过单词边界拆分单词，可以转换为char数组
    static List<String> tokenize(String log) {
        StringBuilder cur = new StringBuilder(); // 记录当前单词
        List<String> words = new ArrayList<>();
        for (char c : log.toCharArray()) {
            // 单词边界
            if (isDelim(c)) {
                if (cur.length() > 0) {
                    words.add(cur.toString());
                    cur.setLength(0); // 清空单词，准备记录后续字符
                }
            } else {
                cur.append(c);
            }
        }
        // 可能存在最后一个单词
        if (cur.length() > 0) {
            words.add(cur.toString());
        }
        return words;
    }

    static boolean isDelim(char c) {
        // 判断是否为单词边界
        return Character.isWhitespace(c)
                || c == ','
                || c == '.'
                || c == '!'
                || c == '?'
                || c == ';'
                || c == ':';
    }
}
