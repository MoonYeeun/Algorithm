package boj;

import java.util.*;

// 백준 1175 배달
// bfs (방향, 배달지 별) 방문처리 여부만 잘 체크해주면 된다
public class Back_1175 {
    static char[][] map;
    static boolean[][][][][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n, m;
    static Queue<pair> queue = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new char[n][m];
        visit = new boolean[n][m][4][2][2]; // n, m, 방향, c방문, d방문
        int idx = 0;
        for(int i = 0 ; i < n ; i++) {
            String str = sc.next();
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'S')
                    queue.add(new pair(i, j, -1, 0, 0, 0)); // 시작점

                if(map[i][j] == 'C' && idx++ == 0)
                    map[i][j] = 'D'; // 구별을 위해 하나만 D로 바꿔줌
            }
        }
        System.out.println(bfs());
    }
    static int bfs() {
        int answer = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            pair pair = queue.poll();

            if(pair.v1 == 1 && pair.v2 == 1) {
                answer = Math.min(answer, pair.cnt);
                break;
            }

            for(int i = 0 ; i < 4 ; i++) {
                if(i == pair.dir) continue; // 전과 같은 방향일 경우

                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];

                int c = pair.v1;
                int d = pair.v2;

                if(nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == '#'|| visit[nx][ny][i][c][d])
                    continue;

                if(map[nx][ny] == 'C') c = 1;
                if(map[nx][ny] == 'D') d = 1;

                visit[nx][ny][i][c][d] = true;
                queue.add(new pair(nx, ny, i, pair.cnt+1, c, d));
            }
        }
        if(answer == Integer.MAX_VALUE) answer = -1;
        return answer;
    }
    static class pair {
        int x, y, dir, cnt, v1, v2;

        pair(int x, int y, int dir, int cnt, int v1, int v2) {
            this.x = x;
            this.y = y;
            this.dir = dir; // 이전 방향 기록
            this.cnt = cnt; // 시간
            this.v1 = v1; // 배달지 1 방문
            this.v2 = v2; // 배달지 2 방문
        }
    }
}
