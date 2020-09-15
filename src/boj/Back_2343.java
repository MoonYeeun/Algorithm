package boj;

import java.util.*;

// 백준 2343 기타레슨
// 이분탐색
public class Back_2343 {
    static int n, m;
    static int[] time;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        time = new int[n];

        for (int i = 0; i < n; i++) {
            time[i] = sc.nextInt();
        }

        int min = 1;
        int max = 1000000000;
        int ans = 0;
        while (min <= max) {
            int mid = (min + max) / 2;

            if (search(mid)) {
                max = mid - 1;
                ans = mid;
            } else min = mid + 1;
        }
        System.out.println(ans);
    }

    static boolean search(int mid) {
        int cnt = 1;
        int total = 0;

        for (int i = 0; i < n; i++) {
            if (time[i] > mid) return false;
            total += time[i];

            if (total > mid) {
                cnt++;
                total = time[i];
            }
        }
        return cnt <= m;
    }
}
