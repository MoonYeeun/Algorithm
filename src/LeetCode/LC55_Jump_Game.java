package LeetCode;

// 55. Jump Game
public class LC55_Jump_Game {
    static boolean[] visit;

    public static void main(String[] args) {
        //int[] nums = {2, 3, 1, 1, 4};
        //int[] nums = {3, 2, 1, 0, 4};
        int[] nums = {2, 5, 0, 0};
        boolean result = canJump(nums);

        System.out.println(result);
    }

    public static boolean canJump(int[] nums) {
        visit = new boolean[nums.length];

        return dfs(0, nums);
    }

    public static boolean dfs(int idx, int[] nums) {
        if (idx == nums.length - 1) return true;
        if (visit[idx]) return false;

        visit[idx] = true;

        for (int i = idx + 1; i <= idx + nums[idx]; i++) {
            if (i >= nums.length) continue;
            if (dfs(i, nums)) return true;
        }
        return false;
    }

    // ⭐️ O(n) 풀이
//    public boolean canJump(int[] nums) {
//        int max = 0;
//
//        for(int i = 0; i < nums.length; i++) {
//            if(i > max) return false; // 현재 값에 도달하지 못하는 것 나타냄
//            max = Math.max(max, nums[i] + i);
//        }
//
//        return true;
//    }
}
