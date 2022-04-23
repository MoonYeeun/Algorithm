package LeetCode;

// ⭐️ 34. Find First and Last Position of Element in Sorted Array
// 이분탐색
public class LC34_Find_First_And_LastPosition_Of_Element {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] result = searchRange(nums, target);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] answer = new int[2];
        answer[0] = answer[1] = -1;

        if (nums.length == 0) return answer;

        int start = 0;
        int end = nums.length - 1;
        int mid = 0;

        while (start <= end) {
            mid = (start + end) / 2;

            if (nums[mid] == target) break;
            else if (nums[mid] > target) end = mid - 1;
            else start = mid + 1;
        }

        if (nums[mid] == target) {
            int left = mid;
            int right = mid;

            while (left >= 0 && nums[left] == target) {
                left--;
            }

            while (right < nums.length && nums[right] == target) {
                right++;
            }
            answer[0] = left + 1;
            answer[1] = right - 1;
        }
        return answer;
    }
}
