package daily_question._2026._0522;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。  middle 中等难度
 * <p>
 * 整数除法仅保留整数部分。
 * <p>
 * 你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。
 * <p>
 * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "3+2*2"
 * 输出：7
 * 示例 2：
 * <p>
 * 输入：s = " 3/2 "
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 */
public class BasicCalculator2 {

    public static void main(String[] args) {
        String s = " 3/2 ";
        int res = Solution.calculate(s);
        System.out.println("res=" + res);
    }

    static class Solution {

        public static int where; // 记录当前遍历的位置

        public static int calculate(String s) {
            where = 0; // 每组测试用例都设置为0，开始位置
            return f(s.toCharArray(), 0);
        }


        static int f(char[] ch, int i) {
            // number , ops
            List<Integer> numbers = new ArrayList<>(); // 数字栈
            List<Character> ops = new ArrayList<>(); // 操作栈
            int cur = 0;
            // ) 每层递归只处理当层的结果 也就是遇到)之前的计算
            while (i < ch.length && ch[i] != ')') {
                if (ch[i] == ' ') { // 空字符跳过 ===============
                    i++;
                } else if (ch[i] >= '0' && ch[i] <= '9') {
                    // cur = cur *  10 + Integer.valueOf(ch[i++]);
                    cur = cur * 10 + ch[i++] - '0';
                } else if (ch[i] != '(') {
                    // 遇到了操作符，压入数字栈
                    push(numbers, ops, cur, ch[i++]);
                    // 遇到操作符，cur 必须清零，重新开始记下一个数字
                    cur = 0;
                } else {
                    // 遇到(，交给子过程处理
                    cur = f(ch, i + 1);
                    // where 记录的是当前层所在下标，递归返回时需要用到
                    i = where + 1;
                }
            }
            // 遍历完整个字符串，处理最后一个字符
            push(numbers, ops, cur, '+');
            where = i;
            return compute(numbers, ops);
        }

        static void push(List<Integer> numbers, List<Character> ops, int cur, char op) {
            int n = numbers.size(); //当前数字栈长度
            if (n == 0 || ops.get(n - 1) == '-' || ops.get(n - 1) == '+') {
                numbers.add(cur);
                ops.add(op);
            } else {
                // 符号栈符号为为 * /
                int topNumber = numbers.get(n - 1);
                char topOp = ops.get(n - 1);
                if (topOp == '*') {
                    numbers.set(n - 1, topNumber * cur);
                } else {
                    numbers.set(n - 1, topNumber / cur);
                }
                ops.set(n - 1, op);
            }
        }

        static int compute(List<Integer> numbers, List<Character> ops) {
            int n = numbers.size();
            int ans = numbers.get(0);
            for (int i = 1; i < n; i++) {
                ans += ops.get(i - 1) == '+' ? numbers.get(i) : -numbers.get(i);
            }
            return ans;
        }
    }
}
