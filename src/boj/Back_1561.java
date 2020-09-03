package boj;

import java.util.*;

// ⭐️ 백준 1561 놀이공원
// 1. 마지막 아이가 탑승한 시간 구하기 (이분탐색)
// 2. 탑승 시간 -1 까지 탑승한 아이 수 구하기
// 3. 탑승 시간에 어느 놀이기구 마자막 아이가 탔는지 구하기
public class Back_1561 {
    static int[] ride;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ride = new int[m];
        for (int i = 0; i < m; i++) {
            ride[i] = sc.nextInt();
        }
        //  아이들 수 <= 놀이기구 수
        if (n <= m) {
            System.out.println(n);
            return;
        }
        long time = binarySearch(n); // 마지막 아이가 탑승한 시간
        long child = m; // time -1 까지 탄 아이의 수

        for (int i : ride) {
            child += (time - 1) / i;
        }

        for (int i = 0; i < m; i++) {
            if (time % ride[i] == 0) child++;
            if (child == n) {
                System.out.println(i + 1);
                break;
            }
        }
    }

    static long binarySearch(int n) {
        long min = 0;
        long max = 2000000000L * 30L;
        long ans = 0;

        while (min <= max) {
            long mid = (min + max) / 2; // 놀이기구 탄 시간
            long count = 0; // 해당 시간까지 놀이기구 탄 아이의 수
            for (int i : ride) {
                count += mid / i + 1;
            }

            if (count < n) min = mid + 1;
            else {
                ans = mid;
                max = mid - 1;
            }
        }
        return ans;
    }
}
