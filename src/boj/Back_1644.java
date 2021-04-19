package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1644 소수의 연속합
// 에라토스테네스의 체 + 투 포인터
public class Back_1644 {
    static final int MAX = 4000000;
    static boolean[] isNotPrime = new boolean[MAX + 1];
    static int[] sum = new int[MAX + 1];
    static int idx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        setPrime();
        calPrefixSum();

        int s = 0, e = 0, total = 0;
        int ans = 0;

        while (e < idx) {
            total += sum[e++];

            while (s <= e && total - sum[s] >= n) {
                total -= sum[s++];
            }

            if (total == n) ans++;
        }
        System.out.println(ans);
    }

    public static void setPrime() {
        int max = (int) Math.sqrt(MAX);

        for (int i = 2; i <= max; i++) {
            if (isNotPrime[i]) continue;

            for (int j = 2 * i; j <= MAX; j += i) {
                isNotPrime[j] = true;
            }
        }
    }

    public static void calPrefixSum() {
        for (int i = 2; i <= MAX; i++) {
            if (isNotPrime[i]) continue;
            sum[idx++] += i;
        }
    }
}
