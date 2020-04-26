package boj;
import java.util.*;

// 백준 12865 평범한배낭
// dp
public class Back_12865 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] dp = new int[n+1][m+1];
        int[][] arr = new int[n+1][2];

        for(int i = 1 ; i <= n ; i++) {
            arr[i][0] = sc.nextInt(); // 물건 무게
            arr[i][1] = sc.nextInt(); // 물건 가치
        }

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                dp[i][j] = dp[i-1][j];
                if(j - arr[i][0] >= 0)
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - arr[i][0]] + arr[i][1]);
            }
        }
        System.out.println(dp[n][m]);
    }
}
