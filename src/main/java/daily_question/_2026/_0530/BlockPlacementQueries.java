package daily_question._2026._0530;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockPlacementQueries {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getResults(new int[][]{{1, 2}, {2, 3, 3}, {2, 3, 1}, {2, 2, 2}}));
    }

    static class Solution {
        public List<Boolean> getResults(int[][] queries) {
            List<Boolean> ans = new ArrayList<>();
            // 用一个数组记录当前已经存在障碍物的位置, 用set集合吧。因为不好估计障碍物的数量和下标
            Set<Integer> set = new HashSet<>();

            // 当操作为1 在对应位置标记1 然后通过区间 i, j找一个区间能涵盖 sz长度，并且要求整个区间内无障碍物，如果找不到返回false
            for (int i = 0; i < queries.length; i++) {
                if (queries[i][0] == 1) {
                    // 操作1放置 障碍物
                    set.add(queries[i][1]);
                } else {
                    // 操作2 检查能否放下物品
                    ans.add(check(queries[i], set));
                }
            }
            return ans;
        }

        // q 需要检查的数组 , set 障碍物位置
        Boolean check(int[] q, Set<Integer> set) {
            // l, r双指针
            int x = q[1]; // 尝试放置物品的范围 0 ~ x
            int sz = q[2]; // 物品长度
            // while(l )
            if (x < sz) {
                return false;
            }

            // int l = 0;
            for (int l = 0, r = 0; r <= x; r++) {
                // 检查r位置是否包含障碍物
                if (set.contains(r) && (r - l) < sz) { // r位置为障碍物，并且l ~ r之间无法放下物品
                    //    return (r - l) >= sz;
                    l = r;
                }
                if (r - l >= sz) {
                    return true; // 发现可以放置的范围
                }
            }
            return false;
        }
    }
}
