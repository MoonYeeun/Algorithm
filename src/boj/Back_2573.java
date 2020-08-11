package boj;

import java.util.*;

// 백준 2573 빙산
// bfs
public class Back_2573 {
    static int n, m;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] map;
    static boolean[][] visit;
    static Queue<pair> queue;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        boolean flag = false;
        int ans = 0;
        while (!check()) {
            ans++;
            map = melt();

            visit = new boolean[n][m];
            queue = new LinkedList<>();

            int cnt = 0; // 빙산의 개수 체크
            for(int i = 1 ; i < n-1 ; i++) {
                for(int j = 1 ; j < m-1 ; j++) {
                    if(map[i][j] == 0 || visit[i][j]) continue;

                    cnt++;
                    visit[i][j] = true;
                    queue.add(new pair(i, j));
                    bfs();
                }
            }
            // 분리된 경우
            if(cnt > 1) {
                flag = true;
                break;
            }
        }
        if(flag) System.out.println(ans);
        else System.out.println(0);
    }
    static void bfs() {
        while (!queue.isEmpty()) {
            pair p = queue.poll();

            for(int i = 0 ; i < 4 ; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(map[nx][ny] == 0 || visit[nx][ny]) continue;

                visit[nx][ny] = true;
                queue.add(new pair(nx, ny));
            }
        }
    }
    static int[][] copy() {
        int[][] temp = new int[n][m];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                temp[i][j] = map[i][j];
            }
        }
        return temp;
    }
    static int[][] melt() {
        int[][] temp = copy();

        for(int i = 1 ; i < n-1 ; i++) {
            for(int j = 1 ; j < m-1 ; j++) {
                if(map[i][j] == 0) continue;

                int cnt = 0;
                for(int k = 0 ; k < 4 ; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(map[nx][ny] == 0) cnt++;
                }

                temp[i][j] = map[i][j] - cnt > 0 ? map[i][j] - cnt : 0;
            }
        }
        return temp;
    }
    static boolean check() {
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                if(map[i][j] != 0) return false;
            }
        }
        return true;
    }
    static class pair {
        int x, y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
