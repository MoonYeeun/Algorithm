package boj;

import java.util.*;

// 백준 5427 불
public class Back_5427 {
    static char[][] map;
    static Queue<pair> queue, fire;
    static boolean[][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int w, h;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            w = sc.nextInt();
            h = sc.nextInt();

            map = new char[h][w];
            visit = new boolean[h][w];
            queue = new LinkedList<>();
            fire = new LinkedList<>();

            for(int i = 0 ; i < h ; i++) {
                String str = sc.next();
                map[i] = str.toCharArray();

                for(int j = 0 ; j < w ; j++) {
                    if(map[i][j] == '@') {
                        queue.add(new pair(i, j, 0));
                        visit[i][j] = true;
                    }

                    if(map[i][j] == '*') fire.add(new pair(i, j, 0));
                }
            }
            bfs();
        }
    }
    static void bfs() {
        int size = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            move(); // 불 이동
            while (size-- > 0) {
                pair p = queue.poll();

                for(int i = 0 ; i < 4 ; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];

                    // 출구 도달
                    if(nx < 0 || nx >= h || ny < 0 || ny >= w) {
                        System.out.println(p.cnt + 1);
                        return;
                    }
                    if(map[nx][ny] == '*' || map[nx][ny] == '#' || visit[nx][ny]) continue;

                    visit[nx][ny] = true;
                    queue.add(new pair(nx, ny, p.cnt + 1));
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }
    static void move() {
        int size = fire.size();
        while (size-- > 0) {
            pair f = fire.poll();

            for(int i = 0 ; i < 4 ; i++) {
                int nx = f.x + dx[i];
                int ny = f.y + dy[i];

                if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                if(map[nx][ny] == '*' || map[nx][ny] == '#') continue;

                map[nx][ny] = '*';
                fire.add(new pair(nx, ny, 0));
            }
        }
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
