package daily_question._2026._0715;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.cn/problems/count-mentions-per-user/
public class count_mentions_per_user {

    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<String>> events = new ArrayList<>();
//        案例1 ： error status[ch[2] - '0']++; 加错对象 应该加ans
//        List<String> event1 = new ArrayList<>();
//        event1.add("MESSAGE");
//        event1.add("10");
//        event1.add("id1 id0");
//
//        List<String> event2 = new ArrayList<>();
//        event2.add("OFFLINE");
//        event2.add("11");
//        event2.add("0");
//
//
//        List<String> event3 = new ArrayList<>();
//        event3.add("MESSAGE");
//        event3.add("12");
//        event3.add("ALL");
//
//        events.add(event1);
//        events.add(event2);
//        events.add(event3);
//        System.out.println(Arrays.toString(s.countMentions(2, events)));

//        案例2 每个案例并不是按出现时间排序的，需要进行排序 时间 + 类型
//        [["MESSAGE","2","HERE"],["OFFLINE","2","1"],["OFFLINE","1","0"],["MESSAGE","61","HERE"]]
        List<String> event1 = new ArrayList<>();
        event1.add("MESSAGE");
        event1.add("2");
        event1.add("HERE");

        List<String> event2 = new ArrayList<>();
        event2.add("OFFLINE");
        event2.add("2");
        event2.add("1");


        List<String> event3 = new ArrayList<>();
        event3.add("OFFLINE");
        event3.add("1");
        event3.add("0");

        List<String> event4 = new ArrayList<>();
        event4.add("MESSAGE");
        event4.add("61");
        event4.add("HERE");

        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);

        System.out.println(Arrays.toString(s.countMentions(3, events)));
    }

    static class Solution {
        public int[] countMentions(int numberOfUsers, List<List<String>> events) {
//            排序规则： 当2个消息出现时间不同 按时间排序。时间相同，离线消息排在前面
            events.sort((a, b) -> {
                if (Integer.parseInt(a.get(1)) != Integer.parseInt(b.get(1))) {
                    return Integer.parseInt(a.get(1)) - Integer.parseInt(b.get(1));
                }
                return b.get(0).compareTo(a.get(0));
            });

            int n = numberOfUsers;
            int[] ans = new int[n];
            // 0 代表在线，> 0代表离线时间
            int[] status = new int[n];

            // 遍历
            for (List<String> event : events) {

                // 记录谁离线，以及离线时间
                if (event.get(0).equals("MESSAGE")) {
                    // 消息处理
                    if (event.get(2).equals("ALL")) {
                        for (int i = 0; i < n; i++) {
                            // 全部加1
                            ans[i]++;
                        }
                    } else if (event.get(2).equals("HERE")) {
                        // 检查是否右需要唤醒的
                        int time = Integer.parseInt(event.get(1));
                        for (int i = 0; i < n; i++) {
                            if (status[i] > 0 && status[i] + 60 <= time) {
                                status[i] = 0;
                            }
                        }

                        // 在线次数 + 1
                        for (int i = 0; i < n; i++) {
                            ans[i] = (status[i] == 0) ? ans[i] + 1 : ans[i];
                        }
                    } else {
                        // 指定加1
                        for (String e : event.get(2).split(" ")) {
                            // e : id0
                            char[] ch = e.toCharArray();
//                            status[ch[2] - '0']++; 加错对象 应该加ans
                            ans[ch[2] - '0']++;
                        }
                    }
                } else {
                    // OFFLINE
                    for (String e : event.get(2).split("")) {
                        int id = Integer.parseInt(e);
                        status[id] = Integer.parseInt(event.get(1));
                    }
                }
            }

            return ans;
        }
    }
}
