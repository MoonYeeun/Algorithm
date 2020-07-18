package boj;

import java.util.*;

// 백준 17244 아맞다우산
// 비트마스킹으로 챙긴 물건 체크
public class Back_17244 {
    static int n, m;
    static boolean[][][] visit;
    static char[][] map;
    static Queue<pair> queue = new LinkedList<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 가로
        m = sc.nextInt(); // 세로

        map = new char[m][n];
        visit = new boolean[m][n][(1<<5)-1];

        int num = 0;
        for(int i = 0 ; i < m ; i++) {
            String str = sc.next();
            map[i] = str.toCharArray();

            for(int j = 0 ; j < n ; j++) {
                if(map[i][j] == 'X') map[i][j] = (char)(num++ + '0');
                if(map[i][j] == 'S') {
                    queue.add(new pair(i, j, 0, 0));
                    visit[i][j][0] = true;
                }
            }
        }
        bfs(num);
    }
    static void bfs(int num) {
        while (!queue.isEmpty()) {
            pair p = queue.poll();

            // 출구이고 모든 물건 다 챙긴 경우
            if(map[p.x][p.y] == 'E' && p.bit == (1<<num)-1) {
                System.out.println(p.cnt);
                return;
            }

            for(int i = 0 ; i < 4 ; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                int bit = p.bit;

                if(nx < 0 || nx >= m || ny < 0 || ny >= n || map[nx][ny] == '#') continue;

                if(Character.isDigit(map[nx][ny])) bit |= (1<<(map[nx][ny]- '0'));
                if(visit[nx][ny][bit]) continue;

                visit[nx][ny][bit] = true;
                queue.add(new pair(nx, ny, p.cnt + 1, bit));
            }
        }
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
