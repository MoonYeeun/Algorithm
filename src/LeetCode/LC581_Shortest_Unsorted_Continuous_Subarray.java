package LeetCode;

// 581. Shortest Unsorted Continuous Subarray
// 루프 2번 돌면서 정렬되지 않은 가장 왼쪽 - 오른쪽 지점 찾기
// left : 가장 끝 인덱스-1부터 0까지 중 최소보다 큰 값일 경우 갱신
// right : 인덱스 1부터 끝까지 최대보다 작은 값일 경우 갱신
public class LC581_Shortest_Unsorted_Continuous_Subarray {
    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        int result = findUnsortedSubarray(nums);

        System.out.println(result);
    }

    public static int findUnsortedSubarray(int[] nums) {
        int right = 0;
        int left = 0;
        int max = nums[0];
        int min = nums[nums.length - 1];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < max) right = i;
            max = Math.max(max, nums[i]);
        }

        for (int j = nums.length - 2; j >= 0; j--) {
            if (nums[j] > min) left = j;
            min = Math.min(min, nums[j]);
        }

        return right == left ? 0 : right - left + 1;
    }
}
