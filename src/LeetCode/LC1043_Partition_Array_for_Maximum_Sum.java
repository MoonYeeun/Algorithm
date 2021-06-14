package LeetCode;

// 1043. Partition Array for Maximum Sum
// dp
public class LC1043_Partition_Array_for_Maximum_Sum {
    public static void main(String[] args) {
        int[] arr = {1, 15, 7, 8, 2, 5, 10};
        int k = 3;
        System.out.println(maxSumAfterPartitioning(arr, k));
    }

    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int[] dp = new int[arr.length];

        int max = arr[0];
        for (int i = 0; i < k; i++) {
            max = Math.max(max, arr[i]);
            dp[i] = max * (i + 1);
        }

        for (int i = k; i < arr.length; i++) {
            max = arr[i];
            for (int j = 1; j <= k; j++) {
                max = Math.max(max, arr[i - j + 1]);
                dp[i] = Math.max(dp[i], dp[i - j] + max * j);
            }
        }

        return dp[arr.length - 1];
    }
}
