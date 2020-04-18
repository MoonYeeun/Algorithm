package boj;

import java.util.*;

// 백준 1197 최소 스패닝 트리
// 트림 알고리즘
// 임의의 정점을 선택해 최소 신장 트리에 추가 & 간선 힙에 저장
// 최소 비용의 간선 중 최소 신장 트리에 추가되지 않은 간선 선택
// 간선이 v - 1개 추가 될 때까지 위 과정 반복
public class Back_1197_1 {
    static ArrayList<pair> list[];
    static boolean[] visit;
    static PriorityQueue<pair> pq = new PriorityQueue<>();
    static int answer, v, e;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        v = sc.nextInt();
        e = sc.nextInt();

        list = new ArrayList[v+1];
        visit = new boolean[v+1];
        for(int i = 1 ; i <= v ; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < e ; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int w = sc.nextInt();

            list[v1].add(new pair(v1, v2, w));
            list[v2].add(new pair(v2, v1, w));
        }
        // 임의의 정점에 연결된 간선 힙에 넣음
        visit[1] = true;
        for(pair pair : list[1]) {
            pq.add(pair);
        }
        trim(); // 트림 알고리즘 적용
        System.out.println(answer);
    }
    static void trim() {
        int cnt = 0;
        while (!pq.isEmpty()) {
            pair pair = pq.poll();

            if(visit[pair.v2]) continue;
            if(cnt >= v - 1) break;

            cnt++;
            answer += pair.weight;
            visit[pair.v2] = true;
            for(pair p : list[pair.v2]) {
                pq.add(p);
            }
        }
    }
    static class pair implements Comparable<pair>{
        int v1, v2, weight;

        pair(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
        @Override
        public int compareTo(pair pair) {
            return this.weight - pair.weight;
        }
    }
}
