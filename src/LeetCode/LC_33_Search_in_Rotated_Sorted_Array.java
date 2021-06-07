package LeetCode;

// 33. Search in Rotated Sorted Array
public class LC_33_Search_in_Rotated_Sorted_Array {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;

        while (s <= e) {
            int mid = (s + e) / 2;

            if (nums[mid] == target) return mid;

            // 앞 부분 정렬된 경우
            if (nums[s] <= nums[mid]) {
                if (nums[s] <= target && nums[mid] >= target) e = mid - 1;
                else s = mid + 1;
            }
            // 뒷 부분 정렬된 경우
            else {
                if (nums[mid] <= target && nums[e] >= target) s = mid + 1;
                else e = mid - 1;
            }
        }
        return -1;
    }
}
