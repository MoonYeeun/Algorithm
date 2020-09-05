package boj;

import java.util.*;

// 백준 4486 녹색 옷 입은 애가 젤다지?
public class Back_4485 {
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int idx = 1;
        while (true) {
            int n = sc.nextInt();

            if (n == 0) break;

            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int result = bfs(n);
            System.out.println("Problem " + idx++ + ": " + result);
        }
    }
    static int bfs(int n) {
        PriorityQueue<pair> pq = new PriorityQueue<>();
        boolean[][] visit = new boolean[n][n];
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        pq.add(new pair(0, 0, map[0][0]));
        visit[0][0] = true;

        while (!pq.isEmpty()) {
            pair p = pq.poll();
            if (p.x == n - 1 && p.y == n - 1) {
                return p.lose;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visit[nx][ny]) continue;

                visit[nx][ny] = true;
                pq.add(new pair(nx, ny, p.lose + map[nx][ny]));
            }
        }
        return 0;
    }
    static class pair implements Comparable<pair> {
        int x, y, lose;

        pair(int x, int y, int lose) {
            this.x = x;
            this.y = y;
            this.lose = lose;
        }

        @Override
        public int compareTo(pair pair) {
            return this.lose - pair.lose;
        }
    }
}
