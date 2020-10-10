package LeetCode;

public class LC152_Maximum_Product_Subarray {
    public static void main(String[] args) {
        int[] nums = {-4, -3, -2};
        int result = maxProduct(nums);

        System.out.println(result);
    }

    static public int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int ans = max;

        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            int tempMax = Math.max(cur, Math.max(max * cur, min * cur));
            int tempMin = Math.min(cur, Math.min(max * cur, min * cur));

            ans = Math.max(ans, tempMax);

            max = tempMax;
            min = tempMin;
        }
        return ans;
    }
}
