package LeetCode;

// 300. Longest Increasing Subsequence
// dp
public class LC300_Longest_Increasing_Subsequence {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 2, 3};

        int result = lengthOfLIS(nums);
        System.out.println(result);
    }

    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {

            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j]);
            }
            dp[i] += 1;

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}
