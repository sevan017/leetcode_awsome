package huawei_od._0629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://blog.csdn.net/qq_45776114/article/details/162315283
 * 云服务安全策略选择
 */
public class CloudSecurityOption {
    public static void main(String[] args) throws IOException {
        // n k 权重和最大
        // weights[i] i + 1策略的权重
        // conflicts i和 i + 1 冲突。无向图

        // 1、处理输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int k = Integer.parseInt(br.readLine().trim());
        String[] wights = br.readLine().trim().split(",");
        String[] conflicts = br.readLine().trim().split(" ");
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = Integer.parseInt(wights[i]);
        }

        int cLength = conflicts.length;
        int[] c = new int[cLength * 2];
        for (int i = 0; i < cLength; i++) {
            String[] s = conflicts[i].split(",");
            c[i * cLength] = Integer.parseInt(s[0]);
            c[i * cLength + 1] = Integer.parseInt(s[1]);
        }
        // 建立无向图。递归选择
        ArrayList<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            // 舍弃0位置
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < cLength; i++) {
            // c[i] -> c[i + 1]
            graph.get(c[i * cLength]).add(c[i * cLength + 1]);
            graph.get(c[i * cLength + 1]).add(c[i * cLength]);
        }

        List<List<Integer>> ans = compute(n, k, w, graph);

        // 处理输出
        for (List<Integer> list : ans) {
            for (int i = 0; i < k; i++) {
                System.out.print(i == k - 1 ? list.get(i) + " " : list.get(i) + ",");
            }
        }
    }

    // 策略选择
    static List<List<Integer>> compute(int n, int k, int[] w, ArrayList<List<Integer>> graph) {
        ArrayList<List<Integer>> ans = new ArrayList<>();
        // 从策略i开始选
        // ans.add(f(1, k, w, graph));
        // n个权重，选择k个
        Map<Integer, List<List<Integer>>> allAnsMap = new HashMap<>();
        int maxW = 0;
        for (int i = 0; i < n - k; i++) {
            // 选择策略i下去递归
            int sumW = 0;
            List<Integer> ansI = f(i, k, w, graph, sumW);
            if (allAnsMap.containsKey(sumW)) {
                allAnsMap.get(sumW).add(ansI);
            } else {
                List<List<Integer>> newAns =  new ArrayList<>();
                newAns.add(ansI);
                allAnsMap.put(sumW, newAns);
            }
            // 更新最大权重
            maxW = Math.max(sumW, maxW);
        }

        return allAnsMap.getOrDefault(maxW, new ArrayList<>());
    }

    static List<Integer> f(int i, int k, int[] w, ArrayList<List<Integer>> graph, int sumW) {
        List<Integer> ans = new ArrayList<>();
        if (k == 0) {
            // k个权重已经选择
            return ans;
        }
        // 选择权重i
        ans.add(i + 1);
        sumW += w[i];
        // 选择策略, 判断需要选择的权重
        // 选择i号权重，需要判断ans 已选权重是否和i冲突
        for (int j : ans) {
            if (graph.get(j).contains(i + 1 + 1)) {
                // graph.get(j)已选权重，graph.get(j).contains(i) 冲突，需要重新选择
                return ans;
            }
        }
        // 选择下一个权重
        return f(i + 1, k - 1, w, graph, sumW);
    }
}
