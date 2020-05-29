package boj;

import java.util.*;

// 백준 1446 지름길
// dp
public class Back_1446 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();

        int[][] fast = new int[n][3];
        for(int i = 0 ; i < n ; i++) {
            fast[i][0] = sc.nextInt(); // 출발점
            fast[i][1] = sc.nextInt(); // 도착점
            fast[i][2] = sc.nextInt(); // 지름길 길이
        }
        int[] dp = new int[d+1];
        for(int i = 1 ; i <= d; i++) {
            dp[i] = dp[i-1] + 1;

            for(int j = 0 ; j < n ; j++) {
                // 해당 위치로의 지름길 있는 경우
                if(fast[j][1] == i) dp[i] = Math.min(dp[i], dp[fast[j][0]] + fast[j][2]);
            }
        }
        System.out.println(dp[d]);
    }
}
