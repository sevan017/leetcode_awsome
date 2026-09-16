package huawei_od._0913;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        long ans = solve(arr, k);
        System.out.println(ans);
    }

    static long solve(int[] a, int k) {
        int n = a.length;
        // 统计每个数字出现的次数
        Map<Integer, Integer> cnt = new TreeMap<>();
        for (int x : a) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        // 计算所有数字去重后的排列数
        long total = fact(n);
        for (int num : cnt.values()) {
            total /= fact(num);
        }

        // 计算两个指定位置相同的排列数 k - 1 和 k
        long bad = 0;
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            int x = entry.getKey();
            int f = entry.getValue();
            if (f < 2) {
                // 数字x 不重复
                continue;
            }
            // 固定2个数为x后，剩余n -2 个位置的排列数。
            // 固定2个位置后，只需要对n - 2位置进行排列
            long cur = fact(n - 2);
            cur /= fact(f - 2);

            for (Map.Entry<Integer, Integer> other : cnt.entrySet()) {
                int y = other.getKey();
                int c = other.getValue();
                if (y != x) {
                    cur /= fact(c);
                }
            }
            bad += cur;
        }
        return total - bad;
    }

    static long fact(int n) {
        // 计算一个数的排列 1 * 2 * 3 ... *n
        long res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }
}
