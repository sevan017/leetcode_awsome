package daily_question._2026._0914;


// https://leetcode.cn/problems/rectangle-overlap/description/?envType=daily-question&envId=2026-09-14
public class RectangleOverlap {
    public static void main(String[] args) {
        System.out.println(Solution.isRectangleOverlap(new int[]{0, 0, 2, 2}, new int[]{1, 1, 3, 3}));
    }

    static class Solution {
        public static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
            // 画出图形很简单，只需要 rec2 x1落在 rec1 的(x1~x2)范围，或者 y1 落在rec1 的(y1~y2)范围
            // 求交集
            int x1 = rec1[0], x2 = rec1[2];
            int x3 = rec2[0], x4 = rec2[2];
            // x1 < x2 为了保证 x1 < x2，既然已经小于其实就没必要了
//            if (x1 < x2) {
            if (x1 > x2) {
                int t = x1;
                x1 = x2;
                x2 = t;
            }

            if (x3 > x4) {
                int t = x3;
                x3 = x4;
                x4 = t;
            }
            // 如果两个图形重叠
            return (x1 < x3 && x2 > x3) || (x1 < x4 && x2 > x4);
        }
    }
}
