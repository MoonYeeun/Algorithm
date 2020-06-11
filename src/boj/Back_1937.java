package boj;

import java.util.*;

// 백준 1937 욕심쟁이 판다
// dfs + dp
public class Back_1937 {
    static int n;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] map, dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        map = new int[n][n];
        dp = new int[n][n];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        int ans = 0;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                ans = Math.max(ans, dfs(i, j, map[i][j]));
            }
        }
        System.out.println(ans);
    }
    static int dfs(int x, int y, int bamboo) {
        if(dp[x][y] != 0) return dp[x][y];

        dp[x][y] = 1;
        for(int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if(map[nx][ny] <= bamboo) continue;

            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny, map[nx][ny]) + 1);
        }
        return dp[x][y];
    }
}
