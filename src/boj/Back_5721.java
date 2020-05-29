package boj;

import java.util.*;

// 백준 5721 사탕줍기대회
// dp
public class Back_5721 {
    static int m, n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            m = sc.nextInt();
            n = sc.nextInt();

            if(m == 0 && n == 0) break;

            int[][] map = new int[m+1][n+1];
            int[] dp = new int[m+1]; // 각 행 중 먹을 수 있는 사탕의 총합
            int[] col = new int[n+1]; // 각 열 중 먹을 수 있는 사탕의 총합

            for(int i = 1 ; i <= m ; i++) {
                for(int j = 1 ; j <= n ; j++) {
                    map[i][j] = sc.nextInt();

                    if(j == 1) col[j] = map[i][j];
                    else col[j] = Math.max(col[j-1], col[j-2] + map[i][j]);
                }
                dp[i] = col[n];
            }
            // 각 행 중 먹을 수 있는 사탕 총합 구하기
            for(int i = 2 ; i <= m ; i++) {
                dp[i] = Math.max(dp[i-1], dp[i-2] + dp[i]);
            }
            System.out.println(dp[m]);
        }
    }
}
