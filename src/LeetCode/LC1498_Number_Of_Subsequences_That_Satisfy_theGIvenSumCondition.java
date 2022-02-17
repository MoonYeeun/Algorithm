package LeetCode;

import java.util.*;

// 1498. Number of Subsequences That Satisfy the Given Sum Condition
// 투 포인터
// Math.pow() 쓰면 왜 안될까.. 이거때매 삽질 엄청했다..
public class LC1498_Number_Of_Subsequences_That_Satisfy_theGIvenSumCondition {
    static final int MOD = 1000000007;

    public static void main(String[] args) {
        int[] nums = {3, 5, 6, 7};
        int target = 9;
        System.out.println(numSubseq(nums, target));
    }

    // 풀이 1
//    public static int numSubseq(int[] nums, int target) {
//        int ans = 0;
//        int len = nums.length;
//        int[] pow = new int[len+1];
//        int j = len - 1;
//
//        Arrays.sort(nums);
//
//        pow[0] = 1;
//        for(int i = 1 ; i < len ; i++) {
//            pow[i] = (2*pow[i-1]) % MOD;
//        }
//
//        for(int i = 0 ; i < len ; i++) {
//            while(i <= j && nums[i] + nums[j] > target) {
//                j--;
//            }
//
//            if(i <= j) {
//                ans = (ans + pow[j-i]) % MOD;
//            }
//        }
//        return ans;
//    }
    // 더 효율적인 코드
    public static int numSubseq(int[] nums, int target) {
        int ans = 0;
        int[] pow = new int[nums.length + 1];
        int l = 0;
        int r = nums.length - 1;

        Arrays.sort(nums);

        pow[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            pow[i] = (2 * pow[i - 1]) % MOD;
        }

        while (l <= r) {
            if (nums[l] + nums[r] > target) r--;
            else {
                ans = (ans + pow[r - l]) % MOD;
                l++;
            }
        }
        return ans;
    }
}
