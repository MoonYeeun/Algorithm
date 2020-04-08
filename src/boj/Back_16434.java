package boj;

import java.util.Scanner;

// 백준 16434 드래곤 앤 던전
// 이분탐색
public class Back_16434 {
    static int[] t = new int[140005];
    static int[] a = new int[140005];
    static int[] h = new int[140005];
    static int n, h_atk;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        h_atk = sc.nextInt();

        for(int i = 0 ; i < n ; i++) {
            t[i] = sc.nextInt();
            a[i] = sc.nextInt();
            h[i] = sc.nextInt();
        }
        long answer = Long.MAX_VALUE;
        // 용사의 최소 생명력, 최대 생명
        long low = 1; long high = Long.MAX_VALUE;
        while (low <= high) {
            long mid = (low + high) / 2;

            if(check(mid)) {
                high = mid - 1;
                answer = Math.min(answer, mid);
                continue;
            }
            low = mid + 1;
        }
        System.out.println(answer);
    }
    static boolean check(long mid) {
        long cur_atk = h_atk; // 용사의 현재 공격력
        long cur_h = mid; // 용사의 현재 생명력

        for(int i = 0 ; i < n ; i++) {
            if(t[i] == 2) {
                cur_h = cur_h + h[i] > mid ? mid : cur_h + h[i];
                cur_atk += a[i];
            }
            if(t[i] == 1) {
                long temp = (h[i] / cur_atk + (h[i] % cur_atk != 0 ? 1 : 0));
                cur_h -= a[i] * (temp - 1);
            }
            if(cur_h <= 0) return false;
        }
        return true;
    }
}
