package boj;

import java.util.*;

// 백준 1445 일요일 아침의 데이트
// 우선순위큐 + bfs
// 방문체크는 {쓰레기 옆,  쓰레기 지난 횟수} 작은 경우만 갱신
public class Back_1445 {
    static int n, m;
    static char[][] map;
    static pair[][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static PriorityQueue<pair> pq = new PriorityQueue<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new char[n][m];
        visit = new pair[n][m];

        for(int i = 0 ; i < n ; i++) {
            String str = sc.next();
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = str.charAt(j);
                visit[i][j] = new pair(0, 0, 1000000, 1000000);

                if(map[i][j] == 'S') {
                    pq.add(new pair(i, j, 0, 0));
                }
            }
        }
        bfs();
    }
    static void bfs() {
        while (!pq.isEmpty()) {
            pair p = pq.poll();

            if(map[p.x][p.y] == 'F') {
                System.out.println(p.g_cnt + " " + p.b_cnt);
                return;
            }

            // 옆에 쓰레기 있는지 체크
            boolean flag = false;
            for(int i = 0 ; i < 4 ; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if(map[p.x][p.y] == '.' && map[nx][ny] == 'g') flag = true;
            }

            for(int i = 0 ; i < 4 ; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                int pass = p.g_cnt;
                int around = p.b_cnt;

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(flag) around++;
                if(map[nx][ny] == 'g') pass++;

                if (visit[nx][ny].g_cnt <= pass && visit[nx][ny].b_cnt <= around) continue;

                pq.add(new pair(nx, ny, pass, around));
                visit[nx][ny] = new pair(0, 0, pass, around);
            }
        }
    }
    static class pair implements Comparable<pair>{
        int x, y, g_cnt, b_cnt;

        pair(int x, int y, int g_cnt, int b_cnt) {
            this.x = x;
            this.y = y;
            this.g_cnt = g_cnt;
            this.b_cnt = b_cnt;
        }
        @Override
        public int compareTo(pair pair) {
            if(pair.g_cnt == this.g_cnt)
                return this.b_cnt - pair.b_cnt;
            return this.g_cnt - pair.g_cnt;
        }
    }
}
