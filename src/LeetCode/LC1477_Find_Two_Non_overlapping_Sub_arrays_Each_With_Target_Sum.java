package LeetCode;

// ⭐️ 투포인터 + dp
// dp 에 가장 짧은 sub-arr 길이 기록
public class LC1477_Find_Two_Non_overlapping_Sub_arrays_Each_With_Target_Sum {
    public static void main(String[] args) {
//        int[] arr = {4,3,2,6,2,3,4};
//        int target = 6;
        int[] arr = {7, 3, 4, 7};
        int target = 7;
        System.out.println(minSumOfLengths(arr, target));
    }

    public static int minSumOfLengths(int[] arr, int target) {
        int[] dp = new int[arr.length];

        int s = 0;
        int total = 0;
        int result = Integer.MAX_VALUE;

        for (int e = 0; e < arr.length; e++) {
            total += arr[e];

            while (total > target) {
                total -= arr[s++];
            }

            if (total == target) {
                int cur = e - s + 1;

                if (s > 0 && dp[s - 1] != Integer.MAX_VALUE) result = Math.min(result, cur + dp[s - 1]);

                dp[e] = Math.min(cur, e == 0 ? Integer.MAX_VALUE : dp[e - 1]);
            } else {
                dp[e] = e == 0 ? Integer.MAX_VALUE : dp[e - 1];
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
