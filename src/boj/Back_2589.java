package boj;

import java.util.*;

// 백준 보물섬
// bfs
// 각 육지에서 최단거리로 갈 수 있는 가장 긴 지점 중 최대값구하기
public class Back_2589 {
    static int n, m;
    static char[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 가로
        m = sc.nextInt(); // 세로

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String row = sc.next();
            map[i] = row.toCharArray();
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'L') answer = Math.max(answer, cal(i, j));
            }
        }
        System.out.println(answer);
    }

    static int cal(int x, int y) {
        Queue<pair> queue = new LinkedList<>();
        boolean[][] visit = new boolean[n][m];

        visit[x][y] = true;
        queue.add(new pair(x, y));
        int ans = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                pair p = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    if (visit[nx][ny] || map[nx][ny] == 'W') continue;

                    visit[nx][ny] = true;
                    queue.add(new pair(nx, ny));
                }
            }
            ans++;
        }
        return ans - 1;
    }

    static class pair {
        int x, y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

