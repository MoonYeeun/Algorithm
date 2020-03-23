package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 16234 인구이동
public class Back_16234 {
    static int[][] map;
    static ArrayList<pair> list;
    static boolean[][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n, l, r;
    static Queue<pair> queue = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();
        r = sc.nextInt();

        map = new int[n][n];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        int answer = 0;
        while (true) {
            int union = 0;
            visit = new boolean[n][n];

            for(int i = 0 ; i < n ; i++) {
                for(int j = 0 ; j < n ; j++) {
                    if(!visit[i][j]) {
                        list = new ArrayList<>();
                        queue.add(new pair(i, j));
                        int result = bfs();
                        if(list.size() == 0) continue;

                        list.add(new pair(i, j));
                        people_move(result);
                        union++;
                    }
                }
            }
            if(union == 0) break;

            answer++;
        }
        System.out.println(answer);
    }
    static int bfs() {
        int cnt = 1; // 연합 칸의 개수
        int total = 0; // 연합의 인구수
        while (!queue.isEmpty()) {
            pair pair = queue.poll();
            visit[pair.x][pair.y] = true;
            total += map[pair.x][pair.y];

            for(int i = 0 ; i < 4 ; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n || visit[nx][ny]) continue;
                if(sub(map[nx][ny], map[pair.x][pair.y])) {
                    visit[nx][ny] = true;
                    list.add(new pair(nx, ny));
                    queue.add(new pair(nx, ny));
                    cnt++;
                }
            }
        }
        return total/cnt;
    }
    static void people_move(int num) {
        for(pair pair : list) {
            map[pair.x][pair.y] = num;
        }
    }
    static boolean sub(int a, int b) {
        if(Math.abs(a - b) >= l && Math.abs(a - b) <= r)
            return true;
        else return false;
    }
    static class pair {
        int x;
        int y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
