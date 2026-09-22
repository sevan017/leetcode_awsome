package daily_question._2026._0922;

import java.util.Arrays;

// https://leetcode.cn/problems/relative-ranks/?envType=problem-list-v2&envId=sorting&
public class RelativeRanks {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRelativeRanks(new int[]{5, 4, 3, 2, 1})));
    }

    public static String[] findRelativeRanks(int[] score) {
        int n = score.length;
        int[][] s_index = new int[n][2];
        // 记录每个分数所在的下标
        for (int i = 0; i < n; i++) {
            s_index[i][0] = score[i];
            s_index[i][1] = i;
        }
        String[] ans = new String[n];
        // 按分数升序排序
        Arrays.sort(s_index, (a, b) -> a[0] - b[0]);
        for (int i = n - 1, f = 4; i >= 0; i--) {
            int index = s_index[i][1];
            if (i == n - 1) {
                ans[index] = "Gold Medal";
            } else if (i == n - 2) {
                ans[index] = "Silver Medal";
            } else if (i == n - 3) {
                ans[index] = "Bronze Medal";
            } else {
                ans[index] = String.valueOf(f++);
            }
        }
        return ans;
    }
}
