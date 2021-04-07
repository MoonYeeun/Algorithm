package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 2992 크면서 작은 수
public class Back_2992 {
    static int ans, x;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();

        x = Integer.parseInt(num);
        ans = Integer.MAX_VALUE;
        cnt = new int[10];

        char[] arr = num.toCharArray();

        for (char c : arr) {
            cnt[c - '0']++;
        }

        find(num.length(), "");
        System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
    }

    public static void find(int len, String cur) {
        if (len == cur.length()) {
            int result = Integer.parseInt(cur);
            if (result > x) ans = Math.min(ans, result);
        }

        for (int i = 0; i < 10; i++) {
            if (cnt[i] > 0) {
                cnt[i]--;
                find(len, cur + i);
                cnt[i]++;
            }
        }
    }
}
