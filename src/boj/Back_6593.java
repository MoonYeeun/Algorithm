package boj;

import java.util.*;

// 백준 6593 상범빌딩
// bfs
public class Back_6593 {
    static char[][][] map;
    static Queue<pair> queue = new LinkedList<>();
    static boolean[][][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[] f = {1, -1};
    static int l, r, c;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            l = sc.nextInt();
            r = sc.nextInt();
            c = sc.nextInt();

            if(l == 0 && r == 0 && c == 0) break;

            map = new char[l][r][c];
            visit = new boolean[l][r][c];

            for(int i = 0 ; i < l ; i++) {
                for(int j = 0 ; j < r ; j++) {
                    String str = sc.next();
                    for(int k = 0 ; k < c ; k++) {
                        map[i][j][k] = str.charAt(k);

                        if(map[i][j][k] == 'S') {
                            queue.add(new pair(i, j, k, 0));
                            visit[i][j][k] = true;
                        }
                    }
                }
                sc.nextLine();
            }
            bfs();
            queue.clear();
        }
    }
    static void bfs() {
        boolean flag = false;
        while (!queue.isEmpty()) {
            pair pair = queue.poll();

            if(map[pair.floor][pair.x][pair.y] == 'E') {
                flag = true;
                System.out.println("Escaped in " + pair.time + " minute(s).");
                break;
            }
            // 동서남북 이동
            for(int i = 0 ; i < 4; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];

                if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if(visit[pair.floor][nx][ny] || map[pair.floor][nx][ny] == '#') continue;

                visit[pair.floor][nx][ny] = true;
                queue.add(new pair(pair.floor, nx, ny, pair.time + 1));
            }
            //상하 층 이동가능한지 확인
            for(int i = 0 ; i < 2 ; i++) {
                int nf = pair.floor + f[i];

                if(nf < 0 || nf >= l || visit[nf][pair.x][pair.y] || map[nf][pair.x][pair.y] == '#') continue;
                visit[nf][pair.x][pair.y] = true;
                queue.add(new pair(nf, pair.x, pair.y, pair.time + 1));
            }
        }
        if(!flag) System.out.println("Trapped!");
    }
    static class pair {
        int floor, x, y, time;

        pair(int floor, int x, int y, int time) {
            this.floor = floor;
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
