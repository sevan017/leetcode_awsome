package daily_question._2026._0628;

import java.util.Arrays;

public class LargestNumber {
    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{3, 300, 34, 5, 9}));
    }

    public static String largestNumber(int[] nums) {
        // 按字典序排序
        Integer[] arr = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, (a, b) -> (b + " " + a).compareTo(a + " " + b));
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            s.append(arr[i]);
        }
        return s.toString();
    }
}
