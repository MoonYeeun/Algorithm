package boj;

import java.util.*;

// 백준 10422 괄호
// 기준 위치 기반으로 왼쪽 오른쪽 개수 나눠서 dp !
public class Back_10422 {
    static int len;
    static long[] dp;
    static final int MOD = 1000000007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            len = sc.nextInt();
            dp = new long[len+1];
            Arrays.fill(dp, -1);

            if(len % 2 != 0) System.out.println(0);
            else System.out.println(check(len));
        }
    }
    static long check(int cur) {
        if(cur == 0) return 1;
        if(dp[cur] != -1) return dp[cur];

        dp[cur] = 0;
        for(int i = 2; i <= cur ; i+= 2) {
            dp[cur] += (check(i-2) * check(cur-i));
            dp[cur] %= MOD;
        }
        return dp[cur];
    }
}
