package boj;

import java.util.*;

// 백준 14391 종이조각
// 1. 세로, 가로 방향으로 자를 지 체크
// 2. 각 방향에 맞게 계산
public class Back_14391 {
    static int n, m, ans;
    static int[][] map, dir;
    // 가로, 세로 방향
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        dir = new int[n][m];
        for(int i = 0 ; i < n ; i++) {
            String str = sc.next();

            for(int j = 0 ; j < m ; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        //cut_paper(0, 0); // 원래 코드

        // 비트마스킹 ver
        for(int i = 0 ; i < (1<<(n * m)) ; i++) {
            sum(i);
        }
        System.out.println(ans);
    }
    static void cut_paper(int x, int y) {
        if(y >= m) {
            x++;
            y = 0;
        }
        if(x >= n) {
            cal();
            return;
        }
        // 세로 방향으로 자르기
        dir[x][y] = 1;
        cut_paper(x, y + 1);
        // 가로 방향으로 자르기
        dir[x][y] = 0;
        cut_paper(x, y + 1);
    }
    static void cal() {
        boolean[][] visit = new boolean[n][m];
        int total = 0;

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                if(visit[i][j]) continue;

                int cur = dir[i][j];
                int x = i; int y = j;
                StringBuilder sb = new StringBuilder();

                while (true) {
                    if(x < 0 || x >= n || y < 0 || y >= m) break;
                    if(dir[x][y] != cur) break; // 기준점의 자른 방향이랑 다르면 중단

                    visit[x][y] = true;
                    sb.append(map[x][y] - '0');

                    x = x + dx[cur];
                    y = y + dy[cur];
                }
                total += Integer.parseInt(sb.toString());
            }
        }
        ans = Math.max(ans, total);
    }
    static void sum(int bit) {
        boolean[][] visit = new boolean[n][m];
        int total = 0;

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                if(visit[i][j]) continue;
                // 기준점 자르는 방향
                int d = (bit & (1<<(i * m + j))) != 0 ? 1 : 0;

                int x = i; int y = j;
                StringBuilder sb = new StringBuilder();

                while (true) {
                    if(x < 0 || x >= n || y < 0 || y >= m) break;

                    int cur = (bit & (1<<(x * m + y))) != 0 ? 1 : 0;
                    if(d != cur) break; // 기준점의 자른 방향이랑 다르면 중단

                    visit[x][y] = true;
                    sb.append(map[x][y] - '0');

                    x = x + dx[cur];
                    y = y + dy[cur];
                }
                total += Integer.parseInt(sb.toString());
            }
        }
        ans = Math.max(ans, total);
    }
}
