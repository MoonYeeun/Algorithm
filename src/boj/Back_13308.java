package boj;

import java.io.*;
import java.util.*;

// 백준 13308 주유소
// 다익스트라
public class Back_13308 {
    static int n, m;
    static boolean[][] visit;
    static int[] cost;
    static ArrayList<Info> list[];
    static PriorityQueue<pair> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cost = new int[n+1];
        visit = new boolean[n+1][2501]; // 도시, 리터당 가격
        list = new ArrayList[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= n ; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            list[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[c1].add(new Info(c2, w));
            list[c2].add(new Info(c1, w));
        }

        pq.add(new pair(1, cost[1], 0));
        dijkstra();
    }
    static void dijkstra() {
        while (!pq.isEmpty()) {
            pair p = pq.poll();

            if(visit[p.city][p.minCost]) continue;

            if(p.city == n) {
                System.out.println(p.total);
                break;
            }

            visit[p.city][p.minCost] = true;
            for(Info nxt : list[p.city]) {
                int min = Math.min(p.minCost, cost[nxt.city]);
                pq.add(new pair(nxt.city, min, p.total + p.minCost * nxt.w));
            }
        }
    }
    static class Info {
        int city, w;

        Info(int city, int w) {
            this.city = city;
            this.w = w;
        }
    }
    static class pair implements Comparable<pair> {
        int city, minCost;
        long total; // 도시, 최소 리터당 가격, 총 비용

        pair(int city, int minCost, long total) {
            this.city = city;
            this.minCost = minCost;
            this.total = total;
        }

        @Override
        public int compareTo(pair pair) {
            return this.total > pair.total ? 1 : -1;
        }
    }
}
