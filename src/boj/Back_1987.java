package boj;

import java.util.Scanner;

// 백준 1987 알파벳
// dfs, 비트마스킹
public class Back_1987 {
    static char[][] map;
    static int r, c, bit, answer;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();

        map = new char[r][c];
        for(int i = 0 ; i < r ; i++) {
            String alpha = sc.next();
            for(int j = 0 ; j < c ; j++) {
                map[i][j] = alpha.charAt(j);
            }
        }
        dfs(0, 0, bit | (1<<(map[0][0] - 'A')), 1);
        System.out.println(answer);
    }
    static void dfs(int x, int y, int bit, int cnt) {
        answer = Math.max(answer, cnt);

        for(int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
            if(((1<<(map[nx][ny]-'A')) & bit) != 0) continue;

            dfs(nx, ny, bit | (1<<(map[nx][ny]-'A')), cnt+1);
        }
    }
}
