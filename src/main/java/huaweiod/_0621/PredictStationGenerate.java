package huaweiod._0621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

// 预测光伏发电量
public class PredictStationGenerate {
    public static void main(String[] args) throws IOException {
        // 按照步骤拆解 1、处理输入 2、计算基准发电量， 判断是否超过电子容量 或者 小于发电能力下限
        // 如果在区间内，直接返回基准发电量
        // 大于最大容量，缩减
        // 小于最小容量，放大
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            // 矩阵数量
            int n = (int) in.nval;
            // 子矩阵
            int[][] sub_arrays = new int[n][3];
            for (int i = 0; i < n; i++) {
                in.nextToken();
                sub_arrays[i][0] = (int) in.nval;
                in.nextToken();
                sub_arrays[i][1] = (int) in.nval;
                in.nextToken();
                sub_arrays[i][2] = (int) in.nval;
            }
            in.nextToken();
            // 电站容量
            int station_capacity = (int) in.nval;
            List<Integer> ans = compute(n, sub_arrays, station_capacity);
            for (Integer a : ans) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }

    static List<Integer> compute(int n, int[][] sub_arrays, int station_capacity) {
        List<Integer> ans = new ArrayList<>();
        // 计算基准容量
        long sum = 0, minSum = 0;
        for (int i = 0; i < n; i++) {
            sum += sub_arrays[i][2];
            minSum += sub_arrays[i][1];
            // 默认情况
            ans.add(sub_arrays[i][2]);
        }
        if (sum > station_capacity) {
            // 需要缩减, 总缩减量，可用缩减空间
            long need = sum - station_capacity;
            long availableSum = sum - minSum;
            if (availableSum <= 0) {
                // 没有下调空间
                for (int i = 0; i < n; i++) {
                    ans.set(i, 0);
                }
                return ans; // 提前返回
            }
            // 下调
            for (int i = 0; i < n; i++) {
                int space = sub_arrays[i][2] - sub_arrays[i][1];
                ans.set(i, (int) Math.ceil(ans.get(i) - need * space / (double) availableSum));
            }
        } else if (sum < minSum) {
            // 需要放大
            long need = minSum - sum;
            long availableSum = 0;
            for (int i = 0; i < n; i++){
                availableSum = sub_arrays[i][0] - sub_arrays[i][2];
            }
            if (availableSum <= 0){
                // 没有上调空间
                for (int i = 0; i < n; i++) {
                    ans.set(i, 0);
                }
                return ans; // 提前返回
            }
            for (int i = 0; i < n; i++){
                int space =  sub_arrays[i][0] - sub_arrays[i][2];
                ans.set(i, (int) Math.ceil(ans.get(i) - need * space / (double) availableSum));
            }
        }
//        else {
//            // 不做处理，返回基准发电量
//
//        }
        return ans;
    }
}
