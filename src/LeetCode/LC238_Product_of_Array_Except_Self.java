package LeetCode;

// 238. Product of Array Except Self
// prefix sum + postfix sum
public class LC238_Product_of_Array_Except_Self {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] result = productExceptSelf(nums);
        for (int i : result) System.out.println(i);
    }

    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] prefix = new int[len];
        int[] postfix = new int[len];
        int[] answer = new int[len];

        prefix[0] = nums[0];
        postfix[len - 1] = nums[len - 1];

        int s = 1;
        int e = len - 2;
        while (s < len) {
            prefix[s] = prefix[s - 1] * nums[s];
            postfix[e] = postfix[e + 1] * nums[e];

            s++;
            e--;
        }

        for (int i = 0; i < len; i++) {
            if (i == 0) answer[i] = postfix[i + 1];
            else if (i == len - 1) answer[i] = prefix[i - 1];
            else answer[i] = prefix[i - 1] * postfix[i + 1];
        }
        return answer;
    }
}
