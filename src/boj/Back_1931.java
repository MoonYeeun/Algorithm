package boj;

import java.util.*;

public class Back_1931 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();

            pq.add(new Pair(s, e));
        }

        int ans = 0;
        int cur = -1;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();

            if (p.s < cur) continue;
            ans++;
            cur = p.e;
        }
        System.out.println(ans);
    }

    static class Pair implements Comparable<Pair> {
        int s, e;

        Pair(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Pair pair) {
            if (this.e == pair.e) return this.s - pair.s;
            return this.e - pair.e;
        }
    }
}
