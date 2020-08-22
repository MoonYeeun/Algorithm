package programmers;

import java.util.*;

// 프로그래머스 배달
// 다익스트라
public class Programmers_delivery {
    static final int MAX = 100000000;
    static ArrayList<pair> list[];
    static PriorityQueue<pair> pq = new PriorityQueue<>();
    static int[] dis;
    public static void main(String[] args) {
        int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        System.out.println(solution(5, road, 3));
    }
    static public int solution(int N, int[][] road, int K) {
        int answer = 0;

        list = new ArrayList[N+1];
        dis = new int[N+1];
        Arrays.fill(dis, MAX);

        for(int i = 1 ; i <= N ; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < road.length ; i++) {
            list[road[i][0]].add(new pair(road[i][1], road[i][2]));
            list[road[i][1]].add(new pair(road[i][0], road[i][2]));
        }

        pq.add(new pair(1, 0));
        dis[1] = 0;
        dijkstra();

        for(int i : dis) {
            if(i <= K) answer++;
        }
        return answer;
    }
    static void dijkstra() {
        while (!pq.isEmpty()) {
            pair p = pq.poll();

            if(dis[p.v] < p.w) continue;

            for(pair nxt : list[p.v]) {
                if(dis[p.v] + nxt.w < dis[nxt.v]) {
                    dis[nxt.v] = dis[p.v] + nxt.w;
                    pq.add(new pair(nxt.v, dis[p.v] + nxt.w));
                }
            }
        }
    }
    static class pair implements Comparable<pair> {
        int v, w;

        pair(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(pair pair) {
            return this.w - pair.w;
        }
    }
}
