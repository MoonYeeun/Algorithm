package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 2468 안전영역
public class Back_2468 {
    static int[][] map;
    static boolean[][] visit;
    static int n;
    static int high = 1;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<pair> queue = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        map = new int[n][n];
        visit = new boolean[n][n];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                map[i][j] = sc.nextInt();

                if(high < map[i][j]) high = map[i][j]; // 최대 높이 구하기
            }
        }
        int answer = 1; // 비 안올 경우 고려해서 최소는 1 !!!!!
        for(int h = 1; h < high ; h++) {
            visit = new boolean[n][n];
            int cnt = 0;
            for(int i = 0 ; i < n ; i++) {
                for(int j = 0 ; j < n ; j++) {
                    if(!sink(h, map[i][j]) && !visit[i][j]) {
                        queue.add(new pair(i, j));
                        bfs(h); // 해당 높이 별로 안전영역 구하기
                        cnt++;
                    }
                }
            }
            answer = Math.max(answer, cnt);
        }
        System.out.println(answer);
    }
    static void bfs(int high) {
        while (!queue.isEmpty()) {
            pair pair = queue.poll();
            visit[pair.x][pair.y] = true;

            for(int i = 0 ; i < 4 ; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];

                if(nx >= 0 && nx < n && ny >= 0 && ny < n
                        && !visit[nx][ny] && !sink(high, map[nx][ny])) {
                    visit[nx][ny] = true;
                    queue.add(new pair(nx, ny));
                }
            }
        }
    }
    // 해당 높이보다 낮은지 확인
    static boolean sink(int high, int area) {
        return area <= high ? true : false;
    }

    static class pair{
        int x;
        int y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
