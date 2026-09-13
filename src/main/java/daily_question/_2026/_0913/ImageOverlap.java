package daily_question._2026._0913;

//https://leetcode.cn/problems/image-overlap/description/?envType=daily-question&envId=2026-09-13
public class ImageOverlap {
    public static void main(String[] args) {
//        Solution.largestOverlap1(); // 错误方法
        int[][] img1 = new int[][]{{1, 1, 0}, {0, 1, 0}, {0, 1, 0}}, img2 = new int[][]{{0, 0, 0}, {0, 1, 1}, {0, 0, 1}};
        System.out.println(Solution.largestOverlap2(img1, img2));
    }

    static class Solution {

        public static int largestOverlap1(int[][] img1, int[][] img2) {
            // 思路：根据数据量 30 * 30
            // 将img1移动一次，然后与 img2 比较相同的数量，找出最大的重叠数
            // 有2个问题需要转换为代码逻辑：如何移动？如何计算重叠（数组位置相同，值相同）
            int n = img1.length;
            int ans = 0;
            // 第一个问题如何解决。分析每个位置移动规律

            // 第二个问题很简单。
            //很棒，仅仅靠第二个问题解决了一半的案例 34 / 60 个通过的测试用例
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (img1[i][j] == 1 && img2[i][j] == 1) {
                        ans++;
                    }
                }
            }
            return ans;
        }

        public static int largestOverlap2(int[][] img1, int[][] img2) {
            int n = img1.length;
            int ans = 0;
            // img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
            // 以n = 3为列, i 范围 -2,-1,0,1,2 恰好是边界元素能移动的最大范围 n - 1
            for (int dx = 1 - n; dx < n; dx++) {
                for (int dy = 1 - n; dy < n; dy++) {
                    int cnt1 = 0;
                    // Math.max(-dx, 0) i从2开始，j也从2开始
                    for (int i = Math.max(-dx, 0); i < Math.min(n - dx, n); i++) {
                        for (int j = Math.max(-dy, 0); j < Math.min(n - dy, n); j++) {
                            // 两个数都是1，让cnt1 + 1
                            // i 2 -> j: 2, 1, 0
                            // i 1 -> j: 2, 1, 0
                            // i 0 -> j: 2, 1, 0
                            // 含义(i,j)位置 与 img2(i,j)移动(dx,dy)步后 值为1的
                            // img1 不动，img2 按照dx, dy步伐 从 -2,-1,0,1,2 移动。看两个数数组数字重合部分
                            cnt1 += img1[i][j] * img2[i + dx][j + dy];
                        }
                    }
                    ans = Math.max(ans, cnt1);
                }
            }
            return ans;
        }
    }
}
