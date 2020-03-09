package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 2146 다리만들기
/*
1. 각각의 섬 구별
2. 각 섬 간의 최소 거리 구하기
3. 가장 최소값 리턴
* */
public class Back_2146 {
    static int[][] map;
    static boolean[][] visit;
    static Queue<pair> queue = new LinkedList<>();
    static int n;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        // 섬 구별
        int cnt = 2;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(map[i][j] == 1) {
                    map[i][j] = cnt;
                    queue.add(new pair(i,j));
                    isIsland(cnt);
                    cnt++;
                }
            }
        }
        int answer = 9999999;
        // 섬에서 섬까지의 최소 거리 구하기
        for(int i = 2 ; i < cnt ; i++) {
            visit = new boolean[n][n];
            queue.clear();
            answer = Math.min(answer, bfs(i));
        }
        System.out.println(answer);
    }
    // 각각 다른 섬 구별하는 함수
    static void isIsland(int mark) {
        while(!queue.isEmpty()) {
            pair pair = queue.poll();
            for(int i = 0 ; i < 4 ; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];
                if(nx < n && nx >= 0 && ny < n && ny >= 0
                        && map[nx][ny] == 1) {
                    map[nx][ny] = mark;
                    queue.add(new pair(nx, ny));
                }
            }
        }
    }
    // 섬 간의 거리 구하는 함수
    static int bfs(int current) {
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(map[i][j] == current) {
                    visit[i][j] = true;
                    queue.add(new pair(i, j));
                }
            }
        }
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0 ; i < size ; i++) {
                pair pair = queue.poll();

                for(int j = 0 ; j < 4 ; j++) {
                    int nx = pair.x + dx[j];
                    int ny = pair.y + dy[j];
                    if(nx < n && nx >= 0 && ny < n && ny >= 0) {
                        if(map[nx][ny] != 0 && map[nx][ny] != current)
                            return result;
                        else if(map[nx][ny] == 0 && !visit[nx][ny]) {
                            visit[nx][ny] = true;
                            queue.add(new pair(nx,ny));
                        }
                    }
                }
            }
            result++;
        }
        return -1;
    }
    static class pair {
        int x;
        int y;

        pair(int x, int y) {
            this.x = x; // 세로
            this.y = y; // 가로
        }
    }
}
