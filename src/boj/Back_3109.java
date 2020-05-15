package boj;

import java.util.*;
// 백준 3109 빵집
// 백트래킹 + 그리디
public class Back_3109 {
    static char[][] map;
    static boolean[][] visit;
    // 오른쪽 위, 오른쪽, 오른쪽 아래 순
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    static int r, c;
    static int cnt;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();

        map = new char[r][c];
        visit = new boolean[r][c];
        for(int i = 0 ; i < r ; i++) {
            String str = sc.next();
            for(int j = 0 ; j < c ; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        // 첫번째 열 시작점으로 판단
        for(int i = 0 ; i < r ; i++) {
            if(dfs(i, 0)) cnt++;
        }
        System.out.println(cnt);
    }
    static boolean dfs(int x, int y) {
        // 마지막 열에 도달한 경우
        if(y == c-1) {
            return true;
        }

        for(int i = 0 ; i < 3 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
            if(map[nx][ny] == 'x' || visit[nx][ny]) continue;

            visit[nx][ny] = true;
            if(dfs(nx, ny)) return true;
        }
        return false;
    }
}
