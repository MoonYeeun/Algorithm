package boj;

import java.util.*;
// 백준 11967 불끄기
// Bfs
// 1. 큐 돌면서 해당 좌표에서 킬 수 있는 불 있는지 확인
// 2. 불 킬 수 있는 경우 cnt 증가시키고 불 킨 좌표로 갈 수 있는지 확인
// 3. 4 방향 중 하나라도 방문 체크 되어 있으면 갈 수 있다는 뜻이므로 큐에 좌표 넣기
// 4. 반복
public class Back_11967 {
    static int n, cnt;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visit;
    static boolean[][] light;
    static ArrayList<pair>[] list;
    static Queue<pair> queue = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m = sc.nextInt();

        visit = new boolean[n][n];
        light = new boolean[n][n];
        list = new ArrayList[n*n];

        for(int i = 0 ; i < n*n ; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < m ; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;

            list[x * n + y].add(new pair(a, b));
        }
        bfs();
        System.out.println(cnt+1); // 불 킨 개수 + (1, 1)
    }
    static void bfs() {
        visit[0][0] = true;
        light[0][0] = true;
        queue.add(new pair(0, 0));

        while (!queue.isEmpty()) {
            pair p = queue.poll();

            turnOn(p.x * n + p.y);

            for(int i = 0 ; i < 4 ; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(!light[nx][ny] || visit[nx][ny]) continue;

                visit[nx][ny] = true;
                queue.add(new pair(nx, ny));
            }
        }
    }
    static void turnOn(int idx) {
        for(int i = 0 ; i < list[idx].size() ; i++) {
            pair p = list[idx].get(i);

            if(light[p.x][p.y]) continue;

            light[p.x][p.y] = true;
            cnt++;
            // 해당 위치에 불 킬 수 있는지 체크
            for(int j = 0 ; j < 4 ; j++) {
                int nx = p.x + dx[j];
                int ny = p.y + dy[j];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(!light[nx][ny] || !visit[nx][ny]) continue;
                queue.add(new pair(nx, ny));
                break;
            }
        }
    }
    static class pair {
        int x, y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
