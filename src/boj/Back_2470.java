package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 2470 두 용액
public class Back_2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int s = 0, e = n - 1;
        int min = 0, max = 0;
        int ans = Integer.MAX_VALUE;

        while (s < e) {
            int total = arr[s] + arr[e];

            if (ans > Math.abs(total)) {
                ans = Math.abs(total);
                min = arr[s];
                max = arr[e];

                if (ans == 0) break;
            }

            if (total < 0) s++;
            else e--;
        }
        System.out.println(min + " " + max);
    }
}
