package boj;

import java.util.*;

// 백준 14948 군대탈출하기
// bfs + 우선순위큐
public class Back_14948 {
    static int n, m;
    static int[][] map;
    static int[][] check;
    static boolean[][][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        check = new int[n][m];
        visit = new boolean[n][m][2];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        System.out.println(bfs());
    }
    static int bfs(){
        int answer = Integer.MAX_VALUE;
        PriorityQueue<pair> queue = new PriorityQueue<>();
        queue.add(new pair(0, 0, map[0][0], 0));
        visit[0][0][0] = true;

        while (!queue.isEmpty()) {
            pair pair = queue.poll();

            if(pair.x == n-1 && pair.y == m-1) {
                answer = Math.min(pair.lev, answer);
            }

            for(int i = 0 ; i < 4 ; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];

                if(!isRange(nx, ny) || visit[nx][ny][pair.cnt]) continue;

                int lev = Math.max(map[nx][ny], pair.lev);
                visit[nx][ny][pair.cnt] = true;
                queue.add(new pair(nx, ny, lev, pair.cnt));

                // 띄어 넘지 않은 경우
                if(pair.cnt == 0 && isRange(nx + dx[i], ny + dy[i])
                        && !visit[nx + dx[i]][ny + dy[i]][pair.cnt + 1]) {
                    visit[nx + dx[i]][ny + dy[i]][pair.cnt + 1] = true;
                    lev = Math.max(map[nx+dx[i]][ny+dy[i]], pair.lev);
                    queue.add(new pair(nx + dx[i], ny + dy[i], lev,pair.cnt + 1));
                }
            }
        }
        return answer;
    }
    static boolean isRange(int x, int y) {
        if(x < 0 || x >= n || y < 0 || y >= m) return false;
        return true;
    }
    static class pair implements Comparable<pair>{
        // 세로, 가로, 최소 레벨, 띄어넘은 여부
        int x, y, lev, cnt;

        pair(int x, int y, int lev, int cnt) {
            this.x = x;
            this.y = y;
            this.lev = lev;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(pair pair) {
            return this.lev - pair.lev;
        }
    }
}
