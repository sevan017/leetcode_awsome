package huawei_od._0909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// https://blog.csdn.net/qq_45776114/article/details/164765514
// 考点：递归，记忆化搜索
public class Main3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split("\\s+");

        int newTasks = Integer.parseInt(input[0]);
        int budget = Integer.parseInt(input[1]);
        int addCost = Integer.parseInt(input[2]);
        int returnCost = Integer.parseInt(input[3]);

        int[] ans = solve(newTasks, budget, addCost, returnCost);

        System.out.println(ans[0] + " " + ans[1]);
    }

    static int[] solve(int newTasks, int budge, int addCost, int returnCost) {
        if (newTasks == 1) {
            return new int[]{0, 0};
        }

        HashMap<Long, Node> memo = new HashMap<>();
        Node ans = dfs(newTasks, addCost, returnCost, memo);
        if (ans.cost > budge) {
            return new int[]{-1, -1};
        }
        return new int[]{(int) ans.cost, ans.step};
    }

    static class Node {
        long cost;
        int step;

        Node(long cost, int step) {
            this.cost = cost;
            this.step = step;
        }
    }

    // 比较两个方案，优先成本更低，成本相同时操作次数更少
    static Node better(Node a, Node b) {
        if (a.cost != b.cost) {
            return a.cost < b.cost ? a : b;
        }
        return a.step < b.step ? a : b;
    }

    static Node dfs(long x, int addCost, int returnCost, HashMap<Long, Node> memo) {
        if (x == 1) {
            return new Node(0, 0);
        }

        if (memo.containsKey(x)) {
            return memo.get(x);
        }

        Node ans;

        // 如果x 为偶数，直接平分
        if (x % 2 == 0) {
            Node next = dfs(x / 2, addCost, returnCost, memo);
            ans = new Node(next.cost + 1, next.step + 1);
        } else {
            // 增加一个任务，x -> x + 1 -> (x + 1) / 2
            Node add = dfs(x + 1, addCost, returnCost, memo);
            add = new Node(add.cost + addCost, add.step + 1);

            // 减少一个任务
            Node remove = dfs(x - 1, addCost, returnCost, memo);
            remove = new Node(remove.cost + returnCost, remove.step + 1);

            ans = better(add, remove);
        }
        memo.put(x, ans);
        return ans;
    }


}
