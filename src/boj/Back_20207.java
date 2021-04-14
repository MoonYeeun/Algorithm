package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 20207 달력
// 구현 .. (문제를 잘 읽자)
public class Back_20207 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[] calendar = new int[367];

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            for (int i = s; i <= e; i++) {
                calendar[i]++;
            }
        }

        int ans = 0;
        int row = 0, col = 0;
        for (int i = 1; i < 367; i++) {
            while (calendar[i] != 0) {
                col++;
                row = Math.max(row, calendar[i]);
                i++;
            }

            ans += (col * row);
            col = 0;
            row = 0;
        }
        System.out.println(ans);
    }
}
