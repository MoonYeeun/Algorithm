package boj;

import java.util.*;

// 백준 4179 불
public class Back_4179 {
    static int r, c;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<pair> queue = new LinkedList<>();
    static Queue<pair> fire = new LinkedList<>();
    static boolean[][] visit;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();

        map = new int[r][c];
        visit = new boolean[r][c];

        for(int i = 0 ; i < r ; i++) {
            String str = sc.next();

            for(int j = 0 ; j < c ; j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'F') fire.add(new pair(i, j, 0));

                else if(map[i][j] == 'J') {
                    queue.add(new pair(i, j, 0));
                    visit[i][j] = true;
                }
            }
        }
        bfs();
    }
    static void bfs() {
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 불 확산
            move();

            while (size-- > 0) {
                pair p = queue.poll();

                if(isEscape(p.x, p.y)) {
                    System.out.println(p.cnt + 1);
                    return;
                }

                for(int i = 0 ; i < 4 ; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];

                    if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                    if(visit[nx][ny] || map[nx][ny] != '.') continue;

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
                int fx = f.x + dx[i];
                int fy = f.y + dy[i];

                if(fx < 0 || fx >= r || fy < 0 || fy >= c) continue;
                if(map[fx][fy] == 'F' || map[fx][fy] == '#') continue;

                map[fx][fy] = 'F';
                fire.add(new pair(fx, fy, 0));
            }
        }
    }
    static boolean isEscape(int x, int y) {
        if(x == 0 || x == r-1) return true;
        if(y == 0 || y == c-1) return true;

        return false;
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
