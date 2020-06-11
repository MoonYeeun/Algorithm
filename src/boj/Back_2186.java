package boj;

import java.util.*;

// 백준 2186 문자판
// dfs + dp
public class Back_2186 {
    static int n, m, k;
    static String target;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][][] dp;
    static char[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        map = new char[n][m];
        dp = new int[n][m][81];

        for(int i = 0 ; i < n ; i++) {
            String str = sc.next();
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = str.charAt(j);
                Arrays.fill(dp[i][j], -1);
            }
        }
        target = sc.next(); // 찾아야 하는 문자

        // 찾아야 하는 문자의 첫번째 위치
        int ans = 0;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                if(map[i][j] == target.charAt(0))
                    ans += dfs(i, j,1);
            }
        }
        System.out.println(ans);
    }
    static int dfs(int x, int y, int idx) {
        if(dp[x][y][idx] != -1) return dp[x][y][idx];
        if(idx >= target.length()) return 1;

        dp[x][y][idx] = 0;
        for(int cnt = 1 ; cnt <= k ; cnt++) {
            for(int i = 0 ; i < 4 ; i++) {
                int nx = x + dx[i] * cnt;
                int ny = y + dy[i] * cnt;

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(map[nx][ny] != target.charAt(idx)) continue;

                dp[x][y][idx] += dfs(nx, ny, idx+1);
            }
        }
        return dp[x][y][idx];
    }
}
