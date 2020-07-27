package boj;

import java.util.*;

// 백준 1261 알고스팟
public class Back_1261 {
    static int n, m;
    static char[][] map;
    static int[][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static PriorityQueue<pair> pq = new PriorityQueue<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt(); // 가로
        n = sc.nextInt(); // 세로

        map = new char[n][m];
        visit = new int[n][m];

        for(int i = 0 ; i < n ; i++) {
            String str = sc.next();
            map[i] = str.toCharArray();

            Arrays.fill(visit[i], 100000);
        }

        pq.add(new pair(0, 0, 0));
        visit[0][0] = 0;
        bfs();
    }
    static void bfs() {
        while (!pq.isEmpty()) {
            pair p = pq.poll();

            if(visit[p.x][p.y] < p.cnt) continue;

            if(p.x == n-1 && p.y == m-1) {
                System.out.println(p.cnt);
                return;
            }

            for(int i = 0 ; i < 4 ; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                int cnt = p.cnt;

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(map[nx][ny] == '1') cnt++;

                if(visit[nx][ny] <= cnt) continue;

                visit[nx][ny] = cnt;
                pq.add(new pair(nx, ny, cnt));
            }
        }
    }
    static class pair implements Comparable<pair>{
        int x, y, cnt;

        pair(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(pair pair) {
            return this.cnt - pair.cnt;
        }
    }
}
