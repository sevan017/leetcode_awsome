package daily_question._2026._0718;

// https://leetcode.cn/problems/find-greatest-common-divisor-of-array/description/?envType=daily-question&envId=2026-07-18
public class find_greatest_common_divisor_of_array {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.findGCD(new int[]{7, 5, 6, 8, 3});
    }

    static class Solution {
        public int findGCD(int[] nums) {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);
            }
            System.out.println(max + " " + min);

            while (min != 0) {
//                顺序不能换，如果max 先被min覆盖，那min接下来怎么取都只会是0
                int tmp = min;
                min = max % min;
                max = tmp;
            }
            return max;
        }
    }
}
