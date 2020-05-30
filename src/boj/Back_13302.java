package boj;

import java.util.*;

// 백준 13302 리조트
// dp (탑 다운)
public class Back_13302 {
    static int n, m;
    static int[] arr;
    static int[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n+1];
        dp = new int[n+1][100];

        for(int i = 0 ; i < m ; i++) {
            arr[sc.nextInt()] = 1; // 리조트 갈 수 없는 날
        }
        for(int i = 1 ; i <= n ; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        System.out.println(check(1, 0));
    }
    static int check(int day, int coupon) {
        if(day > n) return 0;
        if(dp[day][coupon] != Integer.MAX_VALUE) return dp[day][coupon];
        if(arr[day] == 1) return check(day + 1, coupon); // 불가능한 날짜면 다음날을 탐색

        dp[day][coupon] = check(day + 1, coupon) + 10000; // 1일권
        dp[day][coupon] = Math.min(dp[day][coupon], check(day + 3, coupon + 1) + 25000); // 3일권
        dp[day][coupon] = Math.min(dp[day][coupon], check(day + 5, coupon + 2) + 37000); // 5일권

        // 쿠폰 3장 이상인 경우 -> 하루 이용권
        if(coupon >= 3) dp[day][coupon] = Math.min(dp[day][coupon], check(day + 1, coupon - 3));

        return dp[day][coupon];
    }
}
