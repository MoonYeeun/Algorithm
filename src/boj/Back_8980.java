package boj;

import java.util.*;

// ⭐️ 백준 8980 택배
// 그리디
// 택배를 받는 마을 기준으로 오름차순 정렬
// 각 마을에서 받을 수 있는 최대 용량 설정
// 각 택배가 보낼 수 있는 최대 한도를 보내는 마을 ~ 받는 마을 -1 까지 빼주기
public class Back_8980 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        int m = sc.nextInt();

        pair[] p = new pair[m + 1];

        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int w = sc.nextInt();

            p[i] = new pair(s, e, w);
        }

        Arrays.sort(p, 0, m);

        int[] maxWeight = new int[n + 1]; // 각 마을에 보낼 수 있는 최대 용량
        Arrays.fill(maxWeight, c);

        int ans = 0;
        for (int i = 0; i < m; i++) {
            int max = Integer.MAX_VALUE; // 보낼 수 있는 최대 양

            for (int j = p[i].s; j < p[i].e; j++) {
                max = Math.min(max, maxWeight[j]);
            }

            if (max >= p[i].w) {
                for (int j = p[i].s; j < p[i].e; j++) {
                    maxWeight[j] -= p[i].w;
                }
                ans += p[i].w;
                continue;
            }

            for (int j = p[i].s; j < p[i].e; j++) {
                maxWeight[j] -= max;
            }
            ans += max;
        }
        System.out.println(ans);
    }

    static class pair implements Comparable<pair> {
        int s, e, w;

        pair(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(pair pair) {
            if (this.e == pair.e) return this.s - pair.s;
            return this.e - pair.e;
        }
    }
}
