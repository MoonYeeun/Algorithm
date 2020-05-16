package boj;

import java.util.*;

// 백준 1102 발전소
// dp + 비트마스크 (켜진 발전소 표현)
public class Back_1102 {
    static int n, p;
    static int[][] cost;
    static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        cost = new int[n][n];
        dp = new int[1<<n];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                cost[i][j] = sc.nextInt();
            }
        }
        String str = sc.next();
        p = sc.nextInt();
        int info = 0;
        int turnon = 0;
        // 켜진 발전소 비트마스크로 표현
        for(int i = 0 ; i < str.length() ; i++) {
            if(str.charAt(i) == 'Y') {
                info = (info | (1<<i));
                turnon++;
            }
        }
        // -1로 초기화
        Arrays.fill(dp, -1);

        if(turnon >= p) System.out.println(0);
        else {
            int ans = solve(info, turnon);
            System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
        }
    }
    static int solve(int info, int cnt) {
        if(cnt >= p) return 0;
        if(dp[info] != -1) return dp[info];

        int ans = Integer.MAX_VALUE;
        for(int i = 0 ; i < n ; i++) {
            // 켜진 발전소
            if((info & (1<<i)) == (1<<i)) {
                // 끌 발전소 찾음
                for(int j = 0 ; j < n ; j++) {
                    if((info & (1<<j)) != (1<<j))
                        ans = Math.min(ans, solve(info | (1<<j), cnt+1) + cost[i][j]);
                }
            }
        }
        return dp[info] = ans;
    }
}
