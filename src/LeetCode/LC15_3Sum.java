package LeetCode;

import java.util.*;

// ⭐️ 15. 3Sum
// 투포인터
public class LC15_3Sum {
    public static void main(String[] args) {

    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);

        int len = nums.length;
        // i 포함해서 나올 수 있는 경우 다 확인
        for (int i = 0; i < len - 2; i++) {
            int l = i + 1;
            int r = len - 1;

            // 이전과 같은 숫자인 경우 동일한 결과 포함될 수 있으므로 넘어감
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[l++], nums[r]));
                    while (l < r && nums[l] == nums[l - 1]) l++; // 이전과 같은 숫자인 경우 넘어감
                } else if (sum < 0) l++;
                else r--;
            }
        }
        return ans;
    }
}


