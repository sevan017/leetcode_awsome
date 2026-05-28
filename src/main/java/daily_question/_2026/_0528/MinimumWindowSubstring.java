package daily_question._2026._0528;

import java.util.Arrays;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
    }
    static class Solution {
        public String minWindow(String s, String t) {
            if(t.length() > s.length()){
                return "";
            }
            // char[] s = str.toCharArray();
            // char[] t = tar.toCharArray();
            int[] cnts = new int[256]; // 所有字符数组
            Arrays.fill(cnts, 0);
            int len = Integer.MAX_VALUE; // 覆盖t最短子串长度
            int debt = t.length();

            for(char i = 0; i < t.length(); i++){
                cnts[t.charAt(i)]--;
            }
//            System.out.println(debt);

            int start = 0; // 从哪个位置开始，发现能覆盖最小子串
            for(int l = 0, r = 0; r < s.length(); r++){

                char ch = s.charAt(r);
                if(cnts[ch] < 0){
                    cnts[ch]++; // 归还对应的欠款
                    debt--; // 总负债减少
                }else {
                    cnts[ch]++; // 不欠款的需要，因为l右移动需要  cnts[s.charAt(l)]
                }
                // 已经还完了
                if(debt == 0){
                    // ch = s.charAt(l);
                    while(cnts[s.charAt(l)] > 0){
                        cnts[s.charAt(l++)]--;
                        // ;
                        // ch = s.charAt(l++);
                    }
                    // l已经在最小窗口左边
                    if((r - l + 1) < len){
                        len = r - l + 1;
                        start = l;
                    }
                }
            }
//            System.out.println(start +" " + len);

            return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
        }
    }
}
