package huaweiod._0621;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 数据包分段传输的最小最大延迟
public class DataPacketTransactionTime {
    // 检验指定每个通道最大延迟限制下所需通道数是否小于等于k
    static boolean judge(int[] nums, int k, int mid) {
        // 所需通道数
        int cnt = 1;
        int sum = 0;

        for (int val : nums) {
            if (val + sum > mid) {
                cnt++;
                sum = 0;
            }
            sum += val;
        }

        return cnt <= k;
    }

    static int getminniDelay(int[] nums, int k) {
        // 上下边界 下边界为最大耗时
        int left = 0;

        // 上边界为耗时和
        int right = 0;

        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        while (left < right) {
            int mid = (left + right) >> 1;

            if (judge(nums, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        String[] parts = input.split(",");

        int[] nums = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }

        int k = Integer.parseInt(br.readLine());

        System.out.println(getminniDelay(nums, k));
    }
}

