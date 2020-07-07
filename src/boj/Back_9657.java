package boj;

import java.util.*;

// 백준 9657 돌게임3
// dp
public class Back_9657 {
    static int[] win = {0, 1, 0, 1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[n+1];
        int len = n < 4 ? n : 4;
        for(int i = 1; i <= len ; i++) {
            dp[i] = win[i];
        }

        // 상근 이길 경우 : 1 창영 이길 경우 : 0
        // 상근 기준 하나라도 진 경우 있으면 -> 이길 수 있으므로 1 아니면 0
        for(int i = 5 ; i <= n ; i++) {
            if(dp[i-1] == 0 || dp[i-3] == 0 || dp[i-4] == 0) dp[i] = 1;
            else dp[i] = 0;
        }

        if(dp[n] == 1) System.out.println("SK");
        else System.out.println("CY");
    }
}
