package huawei_od._0913;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String route = sc.nextLine();
        int target = sc.nextInt();

        System.out.println(solve(route, n, target));
    }

    static int solve(String route, int n, int target) {
        // 拆分路由，
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            // 每个路由器指向自己
            arr[i] = i;
        }
        // 连接关系
        String[] connect = route.split("\\s+");
        for (String c : connect) {
            // 互相关联的两个路由
            String[] r = c.split(",");
            union(arr, Integer.parseInt(r[0]), Integer.parseInt(r[1]));
        }
        int f = find(arr, target);
        int ans = 0;
        for (int i = 0; i < n; i++){
            if (f == arr[i]){
                ans++;
            }
        }
        return ans - 1;
    }

    static void union(int[] arr, int x, int y) {
        int fx = find(arr, x);
        int fy = find(arr, y);
        if (fx != fy) {
            arr[fx] = fy;
        }
    }

    static int find(int[] arr, int i) {
        if (i != arr[i]) {
            arr[i] = find(arr, arr[i]);
        }
        return arr[i];
    }
}
