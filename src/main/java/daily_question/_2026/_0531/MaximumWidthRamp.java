package daily_question._2026._0531;

public class MaximumWidthRamp {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.maxWidthRamp(new int[]{6, 0, 8, 2, 1, 5}));
        System.out.println(solution.maxWidthRamp(new int[]{9,8,1,0,1,9,4,0,4,1}));
    }
    static class Solution {

        static int MAXN = 50001;
        static int[] stack = new int[MAXN];
        static int r;

        public int maxWidthRamp(int[] nums) {
            r = 1;// 表示栈目前存放了下标0位置的下标
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < nums[stack[r - 1]]) { // 小于栈顶元素
                    stack[r++] = i; // 入栈
                }
            }
            // System.out.println("stack: " + stack[2]);

            // 最大坡地一定是最后一个位置高，最开始位置低
            int ans = 0;
            for (int i = nums.length - 1; i >= 0; i--) {
                while (i > r && r > 0 && nums[i] >= nums[stack[r - 1]]) {
                    int cur = stack[--r];
                    ans = Math.max(i - cur, ans);
                }
            }
            return ans;
        }
    }
}
