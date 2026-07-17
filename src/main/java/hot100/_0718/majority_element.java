package hot100._0718;

import java.util.HashMap;

//https://leetcode.cn/problems/majority-element/?envType=study-plan-v2&envId=top-100-liked
public class majority_element {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.majorityElement(new int[]{3, 2, 3});
    }

    /*
     * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
     * 学习：在不借助空间的情况下如何完成这个题目。
     */
    static class Solution2 {
        public int majorityElement(int[] nums) {
            int n = nums.length;
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
//                merge 第一个元素 key
//                第二元素，默认的value值
//                第三个元素是一个函数，如果key已经存在，将原来的value + 当前value值 求和作为新的value值
                map.merge(nums[i], 1, Integer::sum);
            }
//            System.out.println(map.get(3));
            for (int num : map.keySet()) {
                if (map.get(num) > (n / 2)) {
                    return num;
                }
            }
            return 0;
        }
    }

    /**
     * 空间复杂度 O(n)
     */
    static class Solution {
        public int majorityElement(int[] nums) {
            int n = nums.length;
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
//                merge 第一个元素 key
//                第二元素，默认的value值
//                第三个元素是一个函数，如果key已经存在，将原来的value + 当前value值 求和作为新的value值
                map.merge(num, 1, Integer::sum);
            }
            for (int num : map.keySet()) {
                if (map.get(num) > (n / 2)) {
                    return num;
                }
            }
            return 0;
        }
    }
}
