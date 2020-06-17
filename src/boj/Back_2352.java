package boj;

import java.util.*;

// 백준 2352 반도체설계
// LIS (최장증가수열)
public class Back_2352 {
    static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n+1];
        dp = new int[n+1]; // i번째까지 LIS 를 만족하는 가장 작은 값

        for(int i = 1 ; i <= n ; i++) {
            arr[i] = sc.nextInt();
        }

        int idx = 1;
        dp[1] = arr[1];
        for(int i = 1; i <= n ; i++) {
            if(dp[idx] < arr[i]) dp[++idx] = arr[i];
            else {
                int it = lower_bound(idx, arr[i]);
                dp[it] = arr[i];
            }
        }
        System.out.println(idx);
    }
    static int lower_bound(int end, int n) {
        int start = 0;

        while (start < end) {
            int mid = (start + end) / 2;

            if (dp[mid] >= n) end = mid;
            else start = mid + 1;
        }
        return end;
    }
}
