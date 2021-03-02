package LeetCode;

// 930. Binary Subarrays With Sum
// ⭐️ prefixSum + memoization
public class LC930_Binary_Subarrays_With_Sum {
    public static void main(String[] args) {
        int[] A = {1, 0, 1, 0, 1};
        int S = 2;

        int ans = numSubarraysWithSum(A, S);
        System.out.println(ans);
    }

    public static int numSubarraysWithSum(int[] arr, int target) {
        int[] mem = new int[30000];
        mem[0] = 1;
        int e = 0;
        int sum = 0;
        int ans = 0;

        while (e < arr.length) {
            sum += arr[e++];

            if (sum - target >= 0) ans += mem[sum - target];
            mem[sum]++;
        }
        return ans;
    }
}
