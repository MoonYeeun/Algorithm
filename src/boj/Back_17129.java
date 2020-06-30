package boj;

import java.util.*;

// 백준 17129 윌리암슨수액빨이딱따구리가 정보섬에 올라온 이유
public class Back_17129 {
    static int n, m;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visit;
    static Queue<pair> queue = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        visit = new boolean[n][m];
        for(int i = 0 ; i < n ; i++) {
            String loc = sc.next();

            for(int j = 0 ; j < m ; j++) {
                map[i][j] = loc.charAt(j) - '0';

                if(map[i][j] == 2) {
                    queue.add(new pair(i, j, 0));
                    visit[i][j] = true;
                }
            }
        }
        bfs();
    }
    static void bfs() {
        while (!queue.isEmpty()) {
            pair p = queue.poll();

            if(map[p.x][p.y] > 2) {
                System.out.println("TAK");
                System.out.println(p.cnt);
                return;
            }

            for(int i = 0 ; i < 4 ; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(visit[nx][ny] || map[nx][ny] == 1) continue;

                visit[nx][ny] = true;
                queue.add(new pair(nx, ny, p.cnt + 1));
            }
        }
        System.out.println("NIE");
    }
    static class pair {
        int x, y, cnt;

        pair(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
