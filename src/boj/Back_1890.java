package boj;

import java.util.*;

// 백준 1890 점프
// dp (top-down으로)
public class Back_1890 {
    static int[][] map;
    static long[][] dp;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        map = new int[n][n];
        dp = new long[n][n];

        for(int i = 0 ; i < n ; i++) {
            Arrays.fill(dp[i], -1); // dp 배열 초기화

            for(int j = 0 ; j < n ; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        System.out.println(check(0, 0));
    }
    static long check(int x, int y) {
        // 목적지 도달
        if(x == n-1 && y == n-1) return 1;
        // 이미 방문
        if(dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0;

        for(int i = 0 ; i < 2 ; i++) {
            int nx = x + dx[i] * map[x][y];
            int ny = y + dy[i] * map[x][y];

            if(nx >= n || ny >= n) continue;

            dp[x][y] += check(nx, ny);
        }
        return dp[x][y];
    }
}
