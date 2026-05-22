package daily_question._2026._0522;

import java.util.ArrayList;
import java.util.List;

/**
 * 224. 基本计算器
 * 困难  ====
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * <p>
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
 * '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
 * 输入中不存在两个连续的操作符
 * 每个数字和运行的计算将适合于一个有符号的 32位 整数
 */
public class BasicCalculator {

    public static void main(String[] args) {
        String s = "-(2 + 3)";
//        String s = "(1+(4+5+2)-3)+(6+8)";
        int res = Solution.calculate(s);
        System.out.println(res);
    }

    static class Solution {

        // 记录栈的位置
        public static int where;

        public static int calculate(String s) {
            where = 0;
            return f(s.toCharArray(), 0);
        }

        static int f(char[] s, int i) {
            // 当前数字
            int cur = 0;
            List<Integer> numbers = new ArrayList<>();
            List<Character> ops = new ArrayList<>();

            while (i < s.length && s[i] != ')') {
                if (s[i] == ' ') {
                    i++; // 空白字符跳过
                } else if (s[i] >= '0' && s[i] <= '9') {
                    // 遍历到数字
                    cur = cur * 10 + s[i++] - '0';
                } else if (s[i] != '(') {
                    // 运算符 + -，小心运算符 -
                    push(numbers, ops, cur, s[i++]); // 压栈
                    cur = 0; // 为下一个数字准备
                } else {
                    // 遇到 （，进入递归子过程 cur =  f(s, i + 1); 子过程的值必须返回 cur并接住
                   cur =  f(s, i + 1);
                    i = where + 1;
                }
            }
            // 遍历完处理
            // 递归返回更新where
            where = i;
            push(numbers, ops, cur, '+');
            return compute(numbers, ops);
        }

        static void push(List<Integer> numbers, List<Character> ops, int cur, char op) {
//            int n = numbers.size();
            // n == 0，表明是第一个压入数组, 因为没有乘除法，也不用判断
            // op == '-'表明 cur = 0; 同样需要压入数组，但每次cur 都更新了，所以不用单独处理
            // if(n == 0){
            numbers.add(cur);
            ops.add(op);
            // }
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
