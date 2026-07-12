package daily_question._2026._0712;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//https://leetcode.cn/problems/rank-transform-of-an-array/description/
public class rank_transform_of_an_array {

    class Solution {
        // 模拟: 简单题 不要想太难，主要逻辑不是排序。就三步 1、复制数组 2、排序并记录排序后首次的位置 3、遍历原数组填写排序后的位置
        public int[] arrayRankTransform(int[] arr) {
            // 去重: 代码优化，省去set集合
            // 排序
            int[] num = new int[arr.length];
            // 复制数组
            System.arraycopy(arr, 0, num, 0, arr.length);
            Arrays.sort(num); // 升序排序
            HashMap<Integer, Integer> indexMap = new HashMap<>();
            for(int i = 0, index = 1; i < num.length;i++){
                if(!indexMap.containsKey(num[i])){
                    // num[i]首次出现记录下标
                    indexMap.put(num[i], index++);
                }
            }

            int[] ans = new int[arr.length];
            for(int i = 0; i < arr.length; i++){
                ans[i] = indexMap.get(arr[i]);
            }
            return ans;
        }
    }
}
