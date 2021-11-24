package LeetCode;

// 198. House Robber
// 단순 dp (복잡하게 생각하지 말자)
public class LC198_House_Robber {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {
        int[] dp = new int[nums.length];

        if (nums.length < 2) return nums[0];

        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    // 더 빠른 코드 (dp 배열 안만들고 상수로 체킹)
//    public static int rob(int[] nums) {
//        int rob = nums[0];
//        int notRob = 0;
//
//        for(int i = 1 ; i < nums.length ; i++) {
//            int total = nums[i] + notRob;
//            notRob = Math.max(rob, notRob);
//            rob = total;
//        }
//
//        return Math.max(rob, notRob);
//    }
}
