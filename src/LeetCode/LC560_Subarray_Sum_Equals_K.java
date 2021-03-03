package LeetCode;

import java.util.*;

// 560. Subarray Sum Equals K
// prefixsum + hashmap
public class LC560_Subarray_Sum_Equals_K {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;

        System.out.println(subarraySum(nums, k));
    }

    public static int subarraySum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0;
        int ans = 0;
        for (int num : nums) {
            sum += num;

            if (map.containsKey(sum - target)) {
                ans += map.get(sum - target);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}
