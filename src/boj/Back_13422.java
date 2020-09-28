package boj;

import java.util.*;

// 백준 13422 도둑
// 투포인터
public class Back_13422 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();

            int[] jew = new int[n];
            for (int i = 0; i < n; i++) {
                jew[i] = sc.nextInt();
            }

            long total = 0;
            int cnt = 0;
            int ans = 0;
            int s = 0;
            int e = 0;

            while (s < n) {
                total += jew[(e + n) % n];

                if (cnt < m) cnt++;
                else {
                    total -= jew[s++];
                    if (total < k) ans++;
                    if (n == m) break; // 총 마을의 수 == 연속으로 털 수 있는 집 : 같은 집을 선택하는 것이므로 한번만 계산
                }
                e++;
            }
            System.out.println(ans);
        }
    }
}
