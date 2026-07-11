package daily_question._2026._0711;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

//https://leetcode.cn/problems/word-ladder/
public class word_ladder {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }

    static class Solution {
        // bfs 大思路: beginWord.length * wordList.length 最多 5 * 10 ^
        // 每次转换一个单词 cur->next。
        // next == end return level;
        static int MAXN = 5001; // wordList长度
        static String[] queue = new String[MAXN];

        static HashSet<String> visited = new HashSet<>();
        static int l, r;

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            l = r = 0;
            visited.clear();
            int level = 1;
            queue[r++] = beginWord;
            visited.add(beginWord);
            //  每次处理一层
            while (l < r) {
                int size = r - l;
                for (int i = 0; i < size; i++) {
                    String cur = queue[l++];
                    for (String w : wordList) {
                        // 计算next
                        if (check(w, cur) && !visited.contains(w)) {
                            // w, cur 只相差一个字符，且 w 就是w end
//                            if (w == endWord) return level; //  error 1: 字符串相等不能使用 ==
                            if (w.equals(endWord)) {
//                                加上最后一层
                                return level + 1;
                            }
                            // w 可以当作下一个next
                            queue[r++] = w;
                            visited.add(w);
                        }
                    }
                }
//                每层必须++
                level++;
            }

            return 0;
        }

        static boolean check(String next, String cur) {
            int count = 0;

            for (int i = 0; i < next.length(); i++) {
                if (next.charAt(i) != cur.charAt(i)) {
                    count++;
                }
                if (count > 1) {
                    return false;
                }
            }
            return true;
        }
    }
}
