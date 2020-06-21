package boj;

import java.util.*;

// 백준 2194 유닛 이동시키기
public class Back_2914 {
    static int n, m, a, b, k;
    static int[][] map;
    static boolean[][] visit;
    // 상 하 좌 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<pair> queue = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        a = sc.nextInt();
        b = sc.nextInt();
        k = sc.nextInt();

        map = new int[n+1][m+1];
        visit = new boolean[n+1][m+1];

        for(int i = 0 ; i < k ; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            map[x][y] = -1; // 장애물 체크
        }
        // 시작점 위치
        int s_x = sc.nextInt();
        int s_y = sc.nextInt();
        visit[s_x][s_y] = true;
        queue.add(new pair(s_x, s_y, 0));

        // 도착점 위치
        int e_x = sc.nextInt();
        int e_y = sc.nextInt();
        map[e_x][e_y] = 1;

        bfs();
    }
    static void bfs() {

        while (!queue.isEmpty()) {
            pair p = queue.poll();

            if(map[p.x][p.y] == 1) {
                System.out.println(p.cnt);
                return;
            }

            for(int i = 0 ; i < 4 ; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(!check(nx, ny) || visit[nx][ny]) continue;

                visit[nx][ny] = true;
                queue.add(new pair(nx, ny, p.cnt + 1));
            }
        }
        System.out.println(-1);
    }
    static boolean check(int x, int y) {
        for(int i = x ; i < x + a ; i++) {
            for(int j = y ; j < y + b ; j++) {
                if(!isRange(i, j) || map[i][j] == -1) return false;
            }
        }
        return true;
    }
    static boolean isRange(int x, int y) {
        if(x < 1 || x > n || y < 1 || y > m) return false;
        return true;
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
