package huawei_od._0909;

import java.util.Arrays;

// https://blog.csdn.net/qq_45776114/article/details/164765237
//有一个连续的数列，它的前 7 个数为 1 , 2 , 3 , 4 , 5 , 6 , 7。从第 8  个数开始，
// 每个数的值等于它所在位置前面最近连续 7 个数中，最大的两个数之和减去最小的两个数之和。
//例如：第 8 个数的值，是它前面的 7 个数（1 , 2 , 3 , 4 , 5 , 6 , 7）中，最大的两个数（6、7）之和
// 减去最小的两个数（1、2）之和，即 6 + 7 − 1 − 2 = 10。
//现在给定一个位置 n nn，请返回该位置上的数值。
//请实现以下接口：int getResult(int n)
//n：数列中的位置，1 ≤ n ≤ 1000
//返回：数列第 n 个位置上的数值
public class Main {
    static int[] arr = new int[1001];

    // 静态代码块
    static {
        for (int i = 1; i <= 1000; i++) {
            if (i <= 7) {
                arr[i] = i;
            } else {
                // arr[8] = arr[7] + arr[6] - arr[2] - arr[1]
                // arr[9] = arr[8] + arr[7] - arr[3] - arr[2]
                // 会出现负数的情况
                arr[i] = arr[i - 1] + arr[i - 2] - arr[i - 6] - arr[i - 7];
            }
//            System.out.print(arr[i] + ", ");
        }
    }

    public static void main(String[] args) {

//        System.out.println(getResult1(9));
//        for (int i = 0; i <= 1000; i++) {
//            int random = (int) (Math.random() * 30 + 1);
//            int res1 = getResult1(random);
//            int res2 = getResult2(random);
//            if (res2 != res1) {
//                System.out.println(random + "：测试失败，res1=" + res1 + "，res2=" + res2);
//            }
//        }
//        方法1 在数字第20的时候出现了拐点，后续的数字小于前面数字的情况
        int res1 = getResult1(19);
        int res2 = getResult2(19);
        System.out.println(res2 + " " + res1);
        System.out.println("==================================");
        // 结论 方法2正确
        res1 = getResult1(20);
        res2 = getResult2(20);
        System.out.println(res2 + " " + res1);
        System.out.println("==================================");

        res2 = getResult2(20);
        int res3 = getResult3(20);
        System.out.println(res2 + " " + res3);
        System.out.println("==================================");

        for (int i = 0; i <= 100000; i++) {
            int random = (int) (Math.random() * 1000 + 1);
            res2 = getResult2(random);
            res3 = getResult3(random);
            if (res2 != res3) {
                System.out.println(random + "：测试失败，res1=" + res2 + "，res3=" + res3);
                break;
            }
            System.out.println(random + "测试成功");
        }
    }

    static int getResult1(int n) {
        return arr[n];
    }

    static int getResult2(int n) {
        int[] nums = new int[1001];

        for (int i = 1; i <= 7; i++) {
            nums[i] = i;
        }

        for (int i = 8; i <= 1000 && i <= n; i++) {
            int[] t = new int[7];

            for (int j = 0; j < 7; j++) {
                t[j] = nums[i - 7 + j];
            }

            // 升序
            Arrays.sort(t);

            // 最大的两个数之和减去最小的两个数之和
            nums[i] = (t[6] + t[5]) - (t[0] + t[1]);
        }

        return nums[n];
    }

    // 最大的数为1000->对应下标为1000。舍弃下标0
    static int[] nums = new int[1001];

    static {
        // 初始化前7个数字
        for (int i = 1; i <= 7; i++) {
            nums[i] = i;
        }
    }

    static int getResult3(int n) {
        if (nums[n] > 0) {
            return nums[n];
        }
        // 计算第n个数，先获取最近的 7个数字。有可能最近7个数字还未计算出来
        // 计算 8 ~ n
        for (int i = 8; i <= 1000 && i <= n; i++) {
            // 读取前7个数字
            int[] t = new int[7];
            for (int j = 0; j < 7; j++) {
                // i 代表当前要计算的数字。7 是最近的7个数字。假设 i = 9
                // 9 - 7 + 0 = 2, 9 - 7 + 1 = 3,,, 表示 nums[3]~nums[8]的数字需要被取下来
                t[j] = nums[i - 7 + j];
            }
            // 对数组进行排序。默认升序
            Arrays.sort(t);
            nums[i] = t[6] + t[5] - t[0] - t[1];
        }
        return nums[n];
    }

}
