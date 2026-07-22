package daily_question._2026._0722;


import java.util.ArrayList;
import java.util.List;

//https://leetcode.cn/problems/maximize-active-section-with-trade-i/?envType=daily-question&envId=2026-07-21
public class maximize_active_section_with_trade_i {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxActiveSectionsAfterTrade("01010101"));
    }

    static class Solution {
        public int maxActiveSectionsAfterTrade(String s) {
            int n = s.length();
            int cnt1 = 0;
            for (char c : s.toCharArray()) {
                if (c == '1') cnt1++;
            }

            List<Integer> zeroBlocks = new ArrayList<>();
            int i = 0;
            while (i < n) {
                int start = i;
                while (i < n && s.charAt(i) == s.charAt(start)) {
                    i++;
                }
                if (s.charAt(start) == '0') {
                    zeroBlocks.add(i - start);
                }
            }

            int m = zeroBlocks.size();
            if (m < 2) {
                // 没有2块被1分割的相连的0
                return cnt1;
            }
            // 最优增量
            int bestGain = 0;
            for (int j = 0; j < m - 1; j++) {
                bestGain = Math.max(bestGain, zeroBlocks.get(j) + zeroBlocks.get(j + 1));
            }
            return cnt1 + bestGain;
        }
    }
}
