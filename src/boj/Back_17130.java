package boj;

import java.util.*;

// 백준 17130 토끼가 정보섬에 올라온 이유
// 토끼 경로 이동 bfs + 방문체크(dp)
public class Back_17130 {
    static char[][] map;
    static int[][] dp = new int[1001][1001];
    static Queue<pair> queue = new LinkedList<>();
    // → , ↘︎ , ↗
    static int[] dx = {0, 1, -1};
    static int[] dy = {1, 1, 1};
    static int n, m;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new char[n][m];
        for(int i = 0 ; i < n ; i++) {
            String str = sc.next();
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'R') {
                    queue.add(new pair(i, j, 0));
                    dp[i][j] = 0;
                }
                else dp[i][j] = -1;
            }
        }
        System.out.println(bfs());
    }
    static int bfs() {
        int answer = -1;
        while (!queue.isEmpty()) {
            pair pair = queue.poll();

            if(map[pair.x][pair.y] == 'O')
                answer = Math.max(answer, dp[pair.x][pair.y]);

            if(dp[pair.x][pair.y] > pair.cnt) continue;

            for(int i = 0 ; i < 3 ; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];
                int carrot = pair.cnt;

                if(nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == '#') continue;

                if(map[nx][ny] == 'C') carrot++;
                if(dp[nx][ny] >= carrot) continue;

                dp[nx][ny] = carrot;
                queue.add(new pair(nx, ny, carrot));
            }
        }
        return answer;
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
