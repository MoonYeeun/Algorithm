package boj;

import java.util.*;

// 백준 1480 보석 모으기
// dp + 비트마스킹
public class Back_1480 {
    static int[] arr;
    static int[][][] dp;
    static int n, m, c;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        c = sc.nextInt();

        arr = new int[n];
        dp = new int[m][1<<13][c+1];
        for(int i = 0 ; i < n ; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr); // 보석 무게 작은 것 부터
        System.out.println(dfs(0, 0, c));
    }
    static int dfs(int bit, int cur, int w) {
        if(cur == m || bit == (1<<n) - 1) return 0;
        // 이미 체크
        if(dp[cur][bit][w] != 0) return dp[cur][bit][w];

        dp[cur][bit][w] = Math.max(dp[cur][bit][w], dfs(bit, cur + 1, c));
        for(int i = 0 ; i < n ; i++) {
            if((bit & (1<<i)) == (1<<i)) continue;
            if(arr[i] <= w) {
                dp[cur][bit][w] = Math.max(dp[cur][bit][w], dfs(bit | (1<<i), cur, w - arr[i]) + 1);
            }
        }
        return dp[cur][bit][w];
    }
}
