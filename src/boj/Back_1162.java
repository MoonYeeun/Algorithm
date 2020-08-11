package boj;

import java.util.*;

// ⭐️ 백준 1162 도로포장
// 다익스트라 + dp (i 위치에서 j개 포장했을 때의 최소 비용)
// 비용범위 long 으로 잡기 !!!!!
public class Back_1162 {
    static final long MAX = Long.MAX_VALUE;
    static int n, m, k;
    static long[][] dis;
    static ArrayList<pair> list[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        list = new ArrayList[n+1];
        dis = new long[n+1][k+1];
        for(int i = 1; i <= n ; i++) {
            list[i] = new ArrayList<>();
            Arrays.fill(dis[i], MAX);
        }

        for(int i = 0 ; i < m ; i++) {
            int city1 = sc.nextInt();
            int city2 = sc.nextInt();
            int cost = sc.nextInt();

            list[city1].add(new pair(city2, cost, 0));
            list[city2].add(new pair(city1, cost, 0));
        }

        dijkstra();
        long ans = MAX;
        for(int i = 0 ; i <= k ; i++) {
            if(dis[n][i] < ans ) ans = dis[n][i];
        }
        System.out.println(ans);
    }
    static void dijkstra() {
        PriorityQueue<pair> pq = new PriorityQueue<>();
        pq.add(new pair(1, 0, 0));
        dis[1][0] = 0;

        while (!pq.isEmpty()) {
            pair p = pq.poll();

            if(dis[p.city][p.cnt] < p.cost) continue;

            dis[p.city][p.cnt] = p.cost;
            for(pair nxt : list[p.city]) {
                long nextCost = p.cost + nxt.cost;

                if(p.cnt < k && p.cost < dis[nxt.city][p.cnt+1]) {
                    dis[nxt.city][p.cnt+1] = p.cost;
                    pq.add(new pair(nxt.city, p.cost, p.cnt + 1));
                }
                if(nextCost < dis[nxt.city][p.cnt]) {
                    dis[nxt.city][p.cnt] = nextCost;
                    pq.add(new pair(nxt.city, nextCost, p.cnt));
                }
            }
        }
    }
    static class pair implements Comparable<pair>{
        int city, cnt;
        long cost;

        pair(int city, long cost, int cnt) {
            this.city = city;
            this.cost = cost;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(pair pair) {
            return this.cost - pair.cost > 0 ? 1 : -1;
        }
    }
}
