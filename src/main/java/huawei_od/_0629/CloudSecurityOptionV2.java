package huawei_od._0629;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CloudSecurityOptionV2 {
    static int n, k;
    // 当前最大权重和
    static int maxWeightSum = 0;
    // 所有最右方案
    static List<List<Integer>> ans = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();

    // conflict[i] 值对应二进制位置1表示和i冲突
    static int[] conflictMask;
    static int[] weights;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = Integer.parseInt(sc.nextLine());
        k = Integer.parseInt(sc.nextLine());

        String[] w = sc.nextLine().split(",");
        weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(w[i]);
        }
        conflictMask = new int[n];
        if (sc.hasNext()) {
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) {
                for (String p : line.split(" ")) {
                    String[] ab = p.split(",");
                    int a = Integer.parseInt(ab[0]) - 1;
                    int b = Integer.parseInt(ab[1]) - 1;
                    conflictMask[a] |= (1 << b);
                    conflictMask[b] |= (1 << a);
                }
            }
        }

        dfs(0, 0);

        for (int i = 0; i < ans.size(); i++) {
            if (i > 0) System.out.print(" ");
            for (int j = 0; j < ans.get(i).size(); j++) {
                if (j > 0) {
                    System.out.print(",");
                }
                System.out.print(ans.get(i).get(j));
            }
        }
    }

    static void dfs(int idx, int selectedMask) {

        // 剩余数量减枝
        if (path.size() + (n - idx) < k) return;

        if (path.size() == k) { // 收集完一个策略
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += weights[path.get(i) - 1];
            }

            if (sum > maxWeightSum) {
                maxWeightSum = sum;
                ans.clear();
                ans.add(new ArrayList<>(path));
            } else if (sum == maxWeightSum) {
                ans.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = idx; i < n; i++) {
            // 冲突检测
            if ((conflictMask[i] & selectedMask) == 0) {
                path.add(i + 1);
                dfs(i + 1, selectedMask | (1 << i));
                path.remove(path.size() - 1);
            }
        }
    }
}
