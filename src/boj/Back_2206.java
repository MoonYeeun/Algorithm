package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 2206 벽 부수고 이동하기
/*
0 일 때 : 지나갈 수 있다 ( 이동거리 + 1 후 queue 에 넣어줌)
1 일 때 : 공사 횟수 0 일 때만 지나갈 수 있다. ( 이동거리 , 공사횟수 + 1 후 queue 에 넣어줌)
*/
public class Back_2206 {
    static int[][] map;
    static int[][] visit; // 공사 횟수 담을 배열
    static Queue<pair> queue = new LinkedList<>();
    static int n, m;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n+1][m+1];
        visit = new int[n+1][m+1];
        int answer = -1;

        for(int i = 1 ; i <= n ; i++) {
            String[] str = sc.next().split("");
            for(int j = 1 ; j <= m ; j++) {
                map[i][j] = Integer.parseInt(str[j-1]);
                visit[i][j] = Integer.MAX_VALUE;
            }
        }
        queue.add(new pair(1,1, 1, 0));
        visit[1][1] = 0;

        answer = bfs();
        System.out.println(answer);
    }
    static int bfs() {
        while(!queue.isEmpty()) {
            pair pair = queue.poll();
            if(pair.x == n && pair.y == m) return pair.cnt; // 도착지점 도달했을 경우

            for(int i = 0 ; i < 4 ; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];
                // 벽 없고 방문 안했던 곳
                if(nx >= 1 && nx <= n && ny >= 1 && ny <= m &&
                        visit[nx][ny] > pair.drill && map[nx][ny] == 0) {
                    visit[nx][ny] = pair.drill;
                    queue.add(new pair(nx, ny, pair.cnt+1, pair.drill));
                } // 벽 있고 벽 부순 횟수 0 인
                if(nx >= 1 && nx <= n && ny >= 1 && ny <= m &&
                        visit[nx][ny] > pair.drill && map[nx][ny] == 1 && pair.drill == 0) {
                    visit[nx][ny] = pair.drill+1;
                    queue.add(new pair(nx, ny, pair.cnt+1, pair.drill+1));
                }
            }
        }
        return -1;
    }
    static class pair {
        int x;
        int y;
        int cnt; // 이동거리
        int drill; // 벽 부순 횟수

        pair(int x, int y, int cnt, int drill) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.drill = drill;
        }
    }
}
