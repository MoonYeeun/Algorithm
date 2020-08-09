package boj;

import java.util.*;

// ⭐️ 백준 1800 인터넷연결
// 다익스트라 + 이분탐색
// 최소비용 이분탐색으로 정하고 되는지 확인을 다익스트라 !
// 간선의 가중치 최소비용보다 크면 1 같거나 작으면 0으로 계산
public class Back_1800 {
    static final int MAX = 1000000000;
    static int n, cnt, k;
    static ArrayList<pair> list[];
    static int[] dis;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        cnt = sc.nextInt();
        k = sc.nextInt();

        list = new ArrayList[n+1];
        for(int i = 1 ; i <= n ; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < cnt ; i++) {
            int com1 = sc.nextInt();
            int com2 = sc.nextInt();
            int cost = sc.nextInt();

            list[com1].add(new pair(com2, cost));
            list[com2].add(new pair(com1, cost));
        }

        int min = 0 ; int max = MAX;
        int ans = -1;
        // 최소 비용 정하기
        while (min <= max) {
            int mid = (min + max) / 2;

            if(dijkstra(mid)) {
                ans = mid;
                max = mid - 1;
            }
            else min = mid + 1;
        }

        System.out.println(ans);
    }
    static boolean dijkstra(int x) {
        dis = new int[n+1];
        Arrays.fill(dis, MAX);
        dis[1] = 0; // 시작 지점 비용

        PriorityQueue<pair> pq = new PriorityQueue<>();
        pq.add(new pair(1, 0));

        while (!pq.isEmpty()) {
            pair p = pq.poll();

            if(dis[p.cur] < p.cost) continue;

            for(pair i : list[p.cur]) {
                // 최소비용 x 보다 큰 비용의 개수 계산
                int nextCost = p.cost + (i.cost - x <= 0 ? 0 : 1);

                if(nextCost < dis[i.cur]) {
                    dis[i.cur] = nextCost;
                    pq.add(new pair(i.cur, nextCost));
                }
            }
        }
        return dis[n] <= k ? true : false;
    }
    static class pair implements Comparable<pair>{
        int cur, cost;

        pair(int cur, int cost) {
            this.cur = cur;
            this.cost = cost;
        }

        @Override
        public int compareTo(pair pair) {
            return this.cost - pair.cost;
        }
    }
}
