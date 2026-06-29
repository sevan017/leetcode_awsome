package huawei_od._0629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 能量管理系统
 * https://blog.csdn.net/qq_45776114/article/details/162311119
 */
public class EnergyManageSystem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 处理输入
        List<String> energyList = new ArrayList<>();
        // 不知道什么适合结束的状态
        String line;
        while ((line = br.readLine()) != null) {
            // 输入结束
            if (line.trim().isEmpty()) {
                break;
            }
            energyList.add(line);
        }
        int ans = queryEnergy(energyList);
        System.out.println(ans);
    }

    static int queryEnergy(List<String> energyList) {
        int n = energyList.size();
        if (n == 0) {
            return 0;
        }
        // 获取查询版本
        String qVersion = energyList.get(n - 1);
        String[] versionStr = qVersion.trim().split(",");
        // 查询时间范围
        int startTime = Integer.parseInt(versionStr[2]);
        int endTime = Integer.parseInt(versionStr[3]);
        if ("A".equals(versionStr[1])) {
            // 查询全部版本
            return compute(energyList, n - 1, startTime, endTime);
        } else {
            int nv = Integer.parseInt(versionStr[1]);
            // 查询指定范围版本
            return compute(energyList, nv, startTime, endTime);
        }
    }

    static int compute(List<String> energyList, int v, int startTime, int endTime) {
        int totalEnergy = 0;
        for (int i = 0; i < v; i++) {
            String[] command = energyList.get(i).trim().split(",");
            if ("AddProductionRecord".equals(command[0])) {
                if (startTime <= Integer.parseInt(command[3]) && endTime >= Integer.parseInt(command[4])) {
                    totalEnergy += Integer.parseInt(command[2]);
                } else if (startTime <= Integer.parseInt(command[3])) {
                    // 查询区间结束值小于当前记录的结束时间
                    double diff = (double) Integer.parseInt(command[2]) * (endTime - Integer.parseInt(command[3]))
                            / (double) (Integer.parseInt(command[4]) - Integer.parseInt(command[3]));
                    totalEnergy += (int) (diff + 0.5);
                }
            } else if ("AddConsumptionRecord".equals(command[0])) {
                if (startTime <= Integer.parseInt(command[2]) && endTime >= Integer.parseInt(command[3])) {
                    totalEnergy -= Integer.parseInt(command[1]);
                } else if (startTime <= Integer.parseInt(command[3])) {
                    // 查询区间结束值小于当前记录的结束时间
                    double diff = (double) Integer.parseInt(command[1]) * (endTime - Integer.parseInt(command[2]))
                            / (double) (Integer.parseInt(command[3]) - Integer.parseInt(command[2]));
                    totalEnergy -= (int) (diff + 0.5);
                }
            }
        }
        return totalEnergy;
    }
}
