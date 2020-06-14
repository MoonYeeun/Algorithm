package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 11048 이동하기
// dp (Top-down)
public class Back_11048 {
    static int N, M;
    static int[] dx = {1, 0, 1};
    static int[] dy = {0, 1, 1};
    static int[][] map, dp;
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            Arrays.fill(dp[i], -1);

            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(go(1, 1));
    }
    static int go(int x, int y) {
        if(dp[x][y] != -1) return dp[x][y];
        if(x == N && y == M) return map[N][M];

        dp[x][y] = 0;
        for(int i = 0 ; i < 3 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 1 || nx > N || ny < 1 || ny > M) continue;
            dp[x][y] = Math.max(dp[x][y], go(nx, ny)) ;
        }
        dp[x][y] += map[x][y];
        return dp[x][y];
    }
    /* (Bottom-up)
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][M + 1];
        int[][] dp = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());

            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = Math.max(dp[i - 1][j], Math.max(dp[i][j - 1], dp[i - 1][j - 1])) + map[i][j];
            }
        }
        System.out.println(dp[N][M]);
    }
    */
}
