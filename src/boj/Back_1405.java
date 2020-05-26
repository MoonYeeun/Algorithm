package boj;

import java.util.*;

// 백준 1405 미친로봇
// dfs
public class Back_1405 {
    static int n;
    // 동 서 남 북
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static double[] dir;
    static boolean[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dir = new double[4];
        map = new boolean[29][29];

        for(int i = 0 ; i < 4 ; i++) {
            dir[i] = sc.nextInt() / 100.0;
        }
        System.out.println(String.format("%.9f",  dfs(14, 14, 0)));
    }
    static double dfs(int x, int y, int cnt) {
        if(cnt == n) return 1.0;

        double result = 0.0;
        map[x][y] = true;

        for(int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(map[nx][ny]) continue;
            result += dir[i] * dfs(nx, ny, cnt + 1);
        }
        map[x][y] = false;
        return result;
    }
}
