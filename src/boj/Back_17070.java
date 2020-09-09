package boj;

import java.util.*;

// 백준 17070 파이프 옮기기1
// dfs
public class Back_17070 {
    // 자신 오 아래 대각
    static int[] dx = {0, 0, 1, 1};
    static int[] dy = {0, 1, 0, 1};
    static int[][] map;
    static int n, ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        dfs(1, 2, 1);
        System.out.println(ans);
    }

    static void dfs(int x, int y, int dir) {
        if (x == n && y == n) {
            ans++;
            return;
        }

        if (dir == 1 || dir == 2) {
            check(x, y, dir);
        } else {
            check(x, y, 1);
            check(x, y, 2);
        }
        check(x, y, 3);
    }

    static void check(int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (dir == 1 || dir == 2) {
            if (!isRange(nx, ny) || map[nx][ny] == 1) return;
        } else {
            for (int i = 1; i < 4; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];

                if (!isRange(cx, cy) || map[cx][cy] == 1) return;
            }
        }
        dfs(nx, ny, dir);
    }

    static boolean isRange(int x, int y) {
        if (x < 1 || x > n || y < 1 || y > n) return false;
        return true;
    }
}
