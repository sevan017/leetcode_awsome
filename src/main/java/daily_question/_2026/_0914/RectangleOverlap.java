package daily_question._2026._0914;


// https://leetcode.cn/problems/rectangle-overlap/description/?envType=daily-question&envId=2026-09-14
public class RectangleOverlap {
    public static void main(String[] args) {
//        System.out.println(Solution.isRectangleOverlap(new int[]{0, 0, 2, 2}, new int[]{1, 1, 3, 3}));
//        System.out.println(Solution.isRectangleOverlap(new int[]{5, 15, 8, 18}, new int[]{0, 3, 7, 9}));
        System.out.println(Solution.isRectangleOverlap(new int[]{7,8,13,15}, new int[]{10,8,12,20}));
    }

    static class Solution {
        public static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
            // 画出图形很简单，只需要 rec2 x1落在 rec1 的(x1~x2)范围，或者 y1 落在rec1 的(y1~y2)范围
            // 求交集
            int x1 = rec1[0], y1 = rec1[1], x2 = rec1[2], y2 = rec1[3];
            int x3 = rec2[0], y3 = rec2[1], x4 = rec2[2], y4 = rec2[3];
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

            if (y1 > y2) {
                int t = y1;
                y1 = y2;
                y2 = t;
            }

            if (y3 > y4) {
                int t = y3;
                y3 = y4;
                y4 = t;
            }
            // 如果两个图形重叠
            // 忽略了一个重要情况，即使出现 x1,x2,x3,x4或 x1,x2,x4,x3 或 x2,x1,x3,x4。也会出现 y没有公共空间。
            // 所以需要保证x重叠同时，还需要判断y的重叠
            boolean x = (x1 < x3 && x2 > x3) || (x1 < x4 && x2 > x4);
            // 
            boolean y = (y1 < y3 && y2 > y3) || (y1 < y4 && y2 > y4);
            return x && y;
        }
    }
}
