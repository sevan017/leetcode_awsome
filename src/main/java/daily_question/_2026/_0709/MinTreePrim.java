package daily_question._2026._0709;


import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

// Kruskal算法模版（洛谷）
// 静态空间实现
// 测试链接 : https://www.luogu.com.cn/problem/P3366
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以直接通过
public class MinTreePrim {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while(in.nextToken() != StreamTokenizer.TT_EOF){
            int n = (int)in.nval;
            in.nextToken();
            int m = (int)in.nval;

            // 图 u->[v, w] v->[u, w]。u v为节点 w为权重
            ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b)-> a[1] - b[1]); // 小根堆
            // 初始化图。 因为元素元素个数为n，且从1 开始，就想着为了避免下标转换，舍弃0下标的位置，但在初始化时，依然要从0开始
//             for(int i = 1; i <= n; i++) 因为i = 1导致报错
            for(int i = 0; i <= n; i++){
                graph.add(new ArrayList<>());
            }
            for(int i = 0; i < m; i++){
                in.nextToken();
                int u = (int)in.nval;
                in.nextToken();
                int v = (int)in.nval;
                in.nextToken();
                int w = (int)in.nval;

                // 无向图
                graph.get(u).add(new int[]{v, w});
                graph.get(v).add(new int[]{u, w});
            }
            // 将第一个点的数据入堆
            for(int[] node : graph.get(1)){
                heap.offer(node);
            }
            // 因为节点数确定且连续，用数组代替哈希表
            boolean[] set = new boolean[n + 1];
            // 因为第一个元素已经加入
            set[1] = true;

            // 处理堆中的元素
            int nodeCount = 1, ans = 0;
            while(!heap.isEmpty()){
                // 弹出元素
                int[] edge = heap.poll();
                int next = edge[0];
                int cost = edge[1];
                // 目标节点不在集合中
                if(!set[next]){
                    nodeCount++;
                    set[next] = true;
                    ans += cost;

                    // 将next的去向加入堆中
                    for(int[] node : graph.get(next)){
                        heap.offer(node);
                    }
                }
            }
            out.println(nodeCount == n ? ans : "orz");
        }
        out.flush();
        out.close();
        br.close();
    }
}
