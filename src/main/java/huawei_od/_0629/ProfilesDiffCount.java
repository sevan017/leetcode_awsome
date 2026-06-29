package huawei_od._0629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * https://blog.csdn.net/qq_45776114/article/details/162307672
 * 挡位差异统计
 */
public class ProfilesDiffCount {
    public static void main(String[] args) throws IOException {
        // 1、处理输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] str = s.trim().split(",");
        int[] profiles = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            profiles[i] = Integer.parseInt(str[i]);
        }

        s = br.readLine();
        int diff = Integer.parseInt(s);
        // 2、计算
        int ans = compute(diff, profiles);

        // 3、输出
        System.out.println(ans);
    }

    static int compute(int diff, int[] profiles) {
        int count = 0;
        // 分情况处理
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < profiles.length; i++) {
            map.merge(profiles[i], 1, Integer::sum);
        }
        if (diff == 0) {
            // 统计重复数字的个数，数字出现2次数个数
            for (Integer value : map.values()) {
                if (value >= 2) {
                    count++;
                }
            }
        } else {
            // 查看对应的数字 + diff 的值是否存在map中, key是去重的
            for (Integer key : map.keySet()) {
                if (map.containsKey(diff + key)) {
                    count++;
                }
            }
        }
        return count;
    }
}
