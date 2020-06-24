package boj;

import java.util.*;

// 백준 15732 도토리 숨기기
// 이분탐색 / ( 각 규칙 별 도토리 한번에 계산하기 )
public class Back_15732 {
    static int n, k, d;
    static ArrayList<pair> list;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        d = sc.nextInt();

        list = new ArrayList<>();

        int min = Integer.MAX_VALUE; int max = n;
        for(int i = 0 ; i < k ; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            list.add(new pair(a, b, c));

            if(a < min) min = a;
        }

        int ans = 0;
        while (min <= max) {
            int mid = (min + max) / 2;

            if(cal(mid) >= d) {
                ans = mid;
                max = mid - 1;
            }
            else min = mid + 1;
        }
        System.out.println(ans);
    }
    static long cal(int mid) {
        long cnt = 0;

        for(pair p : list) {
            int s = p.a;
            int e = p.b > mid ? mid : p.b;

            if(e >= s) cnt += (e-s) / p.c + 1;
        }
        return cnt;
    }
    static class pair {
        int a, b, c;

        pair(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
