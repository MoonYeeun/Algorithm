package LeetCode;

import java.util.*;

// dfs + dp
public class LC63_Unique_Paths2 {
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static int m, n;
    static int[][] dp;

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int result = uniquePathsWithObstacles(obstacleGrid);
        System.out.println(result);
    }

    static public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1) return 0;
        dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(0, 0, obstacleGrid);
    }

    static int dfs(int x, int y, int[][] obstacleGrid) {
        if (x == m - 1 && y == n - 1 && obstacleGrid[x][y] == 0) return 1;
        if (dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0;
        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= m || ny >= n || obstacleGrid[nx][ny] == 1) continue;

            dp[x][y] += dfs(nx, ny, obstacleGrid);
        }
        return dp[x][y];
    }
}
