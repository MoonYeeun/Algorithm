package boj;

import java.util.*;

// 백준 1562 계단수
// dp + 비트마스킹 (0 ~ 9 까지 다 나왔는지 체크)
// 최종 값에도 모듈러 연산 해주기 !
public class Back_1562 {
    static int n;
    static int[][][] dp;
    static int[] nx = {1, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        dp = new int[10][101][(1<<10)];

        for(int i = 0 ; i < 10 ; i++) {
            for(int j = 0 ; j <= 100 ; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int ans = 0;
        for(int i = 1; i < 10 ; i++) {
            ans += check(i, 0, (1<<i));
            ans %= 1000000000;
        }
        System.out.println(ans);
    }
    static int check(int cur, int idx, int bit) {
        if(idx == n-1) {
            return bit == ((1<<10)-1) ? 1 : 0;
        }
        if(dp[cur][idx][bit] != -1) return dp[cur][idx][bit];

        dp[cur][idx][bit] = 0;

        for(int i = 0 ; i < 2 ; i++) {
            int next = cur + nx[i];
            if(next >= 0 && next < 10)
                dp[cur][idx][bit] += check(next, idx + 1, bit | (1<<next));
        }

        return dp[cur][idx][bit] %= 1000000000;
    }
}
