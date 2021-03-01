package not_solve_myself;

import java.util.*;

// 백준 10986 나머지 합
// (sum[j] - sum[i-1]) % M 이 0인 순서쌍 개수 구하기
// 1. (sum[j] % M) - (sum[i] % M) = 0 => sum[i] % M = sum[j] % M 인 쌍의 개수
// 2. 답 : sum[x] % M = 0 따로 카운트 + 1번에서 구한 쌍의 개수 합
public class Back_10986 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        long[] arr = new long[n + 1];
        long[] cnt = new long[m + 1];
        long ans = 0;

        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
            arr[i] = (arr[i] + arr[i - 1]) % m;

            if (arr[i] == 0) ans++;
            cnt[(int) arr[i]]++;
        }

        for (int i = 0; i < m; i++) {
            ans += cnt[i] * (cnt[i] - 1) / 2;
        }
        System.out.println(ans);
    }
}
