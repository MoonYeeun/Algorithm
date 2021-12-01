package LeetCode;

import java.util.Arrays;

// ⭐️ 473. Matchsticks to Square
// Backtracking
public class LC473_Matchsticks_to_Square {
    public static void main(String[] args) {
        //int[] matchsticks = {1,1,2,2,2};
        int[] matchsticks = {5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3};
        System.out.println(makesquare(matchsticks));
    }

    public static boolean makesquare(int[] matchsticks) {
        if (matchsticks.length < 4) return false;

        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0) return false;

        Arrays.sort(matchsticks);
        return dfs(sum / 4, matchsticks.length - 1, new int[4], matchsticks);
    }

    public static boolean dfs(int target, int idx, int[] side, int[] matchsticks) {
        if (idx == -1) {
            return side[0] == side[1] && side[1] == side[2] && side[2] == side[3] && side[3] == side[0];
        }
        // 들어갈 수 있는 변 찾아서 더해줌
        for (int i = 0; i < 4; i++) {
            if (side[i] + matchsticks[idx] > target) continue;

            side[i] += matchsticks[idx];
            if (dfs(target, idx - 1, side, matchsticks)) return true;
            side[i] -= matchsticks[idx];
        }
        return false;
    }
}
