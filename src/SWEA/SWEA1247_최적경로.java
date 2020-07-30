package SWEA;

import java.util.*;

// SWEA 최적경로 D5
// bfs
public class SWEA1247_최적경로 {
    static int[][] map;
    static boolean[][][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<pair> queue;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int k = 1; k <= t ; k++) {
            int n = sc.nextInt();

            map = new int[101][101];
            visit = new boolean[101][101][(1<<n)];
            queue = new LinkedList<>();

            for(int i = 0 ; i <= 100 ; i++) {
                Arrays.fill(map[i], -1);
            }

            int idx = 0;
            for(int i = 0 ; i < n+2 ; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();

                if(i == 0) {
                    queue.add(new pair(x, y, 0, 0));
                    visit[x][y][0] = true;
                    continue;
                }

                if(i == 1) {
                    map[x][y] = 101;
                    continue;
                }

                map[x][y] = idx++;
            }
            System.out.println("#" + k + " " + bfs(n));
        }
    }
    static int bfs(int n) {
        while (!queue.isEmpty()) {
            pair p = queue.poll();

            if(map[p.x][p.y] == 101 && p.bit == ((1<<n)-1)) {
                return p.cnt;
            }

            for(int i = 0 ; i < 4 ; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                int bit = p.bit;

                if(nx < 0 || nx > 100 || ny < 0 || ny > 100) continue;
                if(map[nx][ny] >= 0 && map[nx][ny] != 101)
                    bit |= (1<<map[nx][ny]);

                if(visit[nx][ny][bit]) continue;

                visit[nx][ny][bit] = true;
                queue.add(new pair(nx, ny, p.cnt + 1, bit));
            }
        }
        return 0;
    }
    static class pair {
        int x, y, cnt, bit;

        pair(int x, int y, int cnt, int bit) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.bit = bit;
        }
    }
}
