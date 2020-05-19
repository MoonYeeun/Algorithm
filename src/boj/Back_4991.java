package boj;

import java.util.*;

// 백준 4991 로봇청소기
// bfs + 비트마스킹
public class Back_4991 {
    static int[][] map;
    static Queue<pair> queue = new LinkedList<>();
    static boolean[][][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int w, h, dirty;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            w = sc.nextInt();
            h = sc.nextInt();

            if(w == 0 && h == 0) break;

            map = new int[h][w];
            visit = new boolean[h][w][(1<<10)];

            int idx = dirty = 0;
            for(int i = 0 ; i < h ; i++) {
                Arrays.fill(map[i], -1);
                String str = sc.next();
                for(int j = 0 ; j < w ; j++) {
                    char c = str.charAt(j);

                    if(c == 'o') {
                        queue.add(new pair(i, j, 0, 0));
                        visit[i][j][0] = true;
                    }
                    if(c == '*') {
                        dirty++;
                        map[i][j] = idx++;
                    }
                    if(c == 'x') map[i][j] = -2;
                }
            }
            bfs();
            queue.clear();
        }
    }
    static void bfs() {
        boolean flag = false;
        while (!queue.isEmpty()) {
            pair pair = queue.poll();

            // 모두 청소한 경우
            if(pair.clean == (1<<dirty)-1) {
                flag = true;
                System.out.println(pair.cnt);
                break;
            }
            for(int i = 0 ; i < 4 ; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];
                int bit = pair.clean;

                if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                // 더러운 칸
                if(map[nx][ny] >= 0) bit |= (1<<map[nx][ny]);
                if(visit[nx][ny][bit] || map[nx][ny] == -2) continue;

                visit[nx][ny][bit] = true;
                queue.add(new pair(nx, ny, pair.cnt + 1, bit));
            }
        }
        if(!flag) System.out.println(-1);
    }
    static class pair {
        int x, y, cnt, clean;

        pair(int x, int y, int cnt, int clean) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.clean = clean;
        }
    }
}
