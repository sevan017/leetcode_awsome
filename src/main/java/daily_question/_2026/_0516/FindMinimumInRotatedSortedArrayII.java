package daily_question._2026._0516;

/**
 * 154. 寻找旋转排序数组中的最小值 II
 */
public class FindMinimumInRotatedSortedArrayII {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 2, 2, 0};
        Solution.findMin(nums);
    }

    static class Solution {
        static public int findMin(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }
            return findM(nums, 0, nums.length - 1);
        }

        static int findM(int[] nums, int left, int right) {
            if (left >= right) {
                return nums[left];
            }
            // 假设最小值在中间
            int mid = left + ((right - left) >> 1);
            // 最边最小值
            int leftMin = nums[left];
            // 右边最小值
            int rightMin = nums[right];
            // 让其中一个是最小值
            if (mid > 0) {
                leftMin = findM(nums, left, mid - 1);
            }
            if (mid < right) {
                rightMin = findM(nums, mid + 1, right);
            }
            // 每次递归比较三个值
            return Math.min(leftMin, Math.min(rightMin, nums[mid]));
        }
    }
}
