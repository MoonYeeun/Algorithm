package boj;

import java.util.ArrayList;
import java.util.Scanner;

// 백준 16197 두 동전
// dfs
public class Back_16197 {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static char[][] map;
    static ArrayList<pair> list = new ArrayList<>();
    static int n, m, answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 세로
        m = sc.nextInt(); // 가로

        map = new char[n][m];
        answer = Integer.MAX_VALUE;

        for(int i = 0 ; i < n ; i++) {
            String str = sc.next();
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'o') {
                    list.add(new pair(i, j));
                }
            }
        }
        dfs(list.get(0).x , list.get(1).x , list.get(0).y, list.get(1).y, 0);

        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }
    static void dfs(int x1, int x2, int y1, int y2, int cnt) {
        if(cnt > 10) return;

        if(isRange(x1, y1) ^ isRange(x2, y2)) {
            answer = Math.min(answer, cnt);
            return;
        }

        for(int i = 0; i < 4 ; i++) {
            int nx1 = x1 + dx[i];
            int nx2 = x2 + dx[i];
            int ny1 = y1 + dy[i];
            int ny2 = y2 + dy[i];

            // 둘 다 범위 벗어나는 경우
            if(!(isRange(nx1, ny1) || isRange(nx2, ny2))) continue;
            // 둘 다 벽인 경우
            if((isRange(nx1, ny1) && isRange(nx2, ny2)) &&
                    isWall(nx1, ny1) && isWall(nx2, ny2)) continue;

            if(isRange(nx2, ny2) && isWall(nx2, ny2)) {
                nx2 = x2;
                ny2 = y2;
            }
            else if(isRange(nx1, ny1) && isWall(nx1, ny1)) {
                nx1 = x1;
                ny1 = y1;
            }
            dfs(nx1, nx2, ny1, ny2, cnt + 1);
        }
    }
    static boolean isRange(int x, int y) {
        if(x < 0 || x >= n || y < 0 || y >= m)
            return false;
        return true;
    }
    static boolean isWall(int x, int y) {
        return map[x][y] == '#';
    }
    static class pair {
        int x, y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
