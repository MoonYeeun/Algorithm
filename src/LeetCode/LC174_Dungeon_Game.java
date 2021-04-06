package LeetCode;

import java.util.*;

// 174. Dungeon Game
// dp ( top - down )
public class LC174_Dungeon_Game {
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static int[][] dp;
    static int m, n;

    public static void main(String[] args) {
        //int[][] dungeon = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        int[][] dungeon = {{-200}};
        int result = calculateMinimumHP(dungeon);

        System.out.println(result);
    }

    public static int calculateMinimumHP(int[][] dungeon) {
        m = dungeon.length;
        n = dungeon[0].length;

        dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        return play(0, 0, dungeon);
    }

    public static int play(int x, int y, int[][] dungeon) {
        if (x == m - 1 && y == n - 1) return Math.max(1, 1 - dungeon[x][y]);
        if (dp[x][y] != -1) return dp[x][y];

        dp[x][y] = Integer.MAX_VALUE;
        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;

            dp[x][y] = Math.min(dp[x][y], play(nx, ny, dungeon) - dungeon[x][y]);
        }
        return dp[x][y] = Math.max(dp[x][y], 1);
    }
}
