package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 16236 아기상어
/*
1. 현재 상어 위치로 부터 상,하,좌,우 이동
2. 지날 수 있는 경우 큐에 삽입 / 먹을 수 있는 경우 물고기 위치, 거리 비교하여 먹을 물고기 결정
3. 더 지날 수 있는 경우가 없을 때,
    먹은 물고기 개수 1 증가, 현재 상어 위치를 먹은 물고기 위치로 바꿈, 걸린 시간 더하기
    먹은 물고기 개수가 상어 크기와 같아진 경우 상어 크기 1 증가
4. 먹을 수 있는 물고기 없을 때까지 위 과정 반복
*/
public class Back_16236 {
    static int n;
    static int shark_size = 2;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] map;
    static boolean[][] visit;
    static Queue<pair> queue = new LinkedList<>(); // 상어가 이동할 수 있는 좌표
    static pair shark;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j] == 9) {
                    shark = new pair(i, j, 0); // 상어 위치
                    map[i][j] = 0;
                }
            }
        }
        int time = 0; // 걸린 시간
        int eat_fish = 0; // 먹은 물고기의 수
        while (true) {
            visit = new boolean[n][n];
            pair min = bfs(shark.x, shark.y);

            if(min.x == n+1 && min.y == n+1) break;

            eat_fish++;
            time += min.dis;
            // 현재 상어 위치 이동
            map[shark.x][shark.y] = 0;
            map[min.x][min.y] = 0;
            shark.x = min.x;
            shark.y = min.y;

            if(eat_fish == shark_size) {
                shark_size++;
                eat_fish = 0;
            }
        }
        System.out.println(time);
    }
    static pair bfs(int x, int y){
        pair min = new pair(n+1, n+1, Integer.MAX_VALUE);
        queue.add(new pair(x, y, 0));
        visit[x][y] = true;
        while(!queue.isEmpty()) {
            pair cur = queue.poll(); // 현재 상어 좌표

            for(int i = 0 ; i < 4 ; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx >= 0 && nx < n && ny >= 0 && ny < n && !visit[nx][ny]) {
                    // 먹을 수 있는 물고기 만난 경우
                    if(map[nx][ny] < shark_size && map[nx][ny] != 0) {
                        visit[nx][ny] = true;
                        if(nx < min.x && min.dis >= cur.dis + 1) {
                            min.x = nx;
                            min.y = ny;
                            min.dis = cur.dis+1;
                        } else if(nx == min.x && ny < min.y && min.dis >= cur.dis + 1) {
                            min.x = nx;
                            min.y = ny;
                            min.dis = cur.dis+1;
                        }
                        continue;
                    } // 지날 수 있는 물고기 만난 경우
                    if(map[nx][ny] == shark_size || map[nx][ny] == 0) {
                        visit[nx][ny] = true;
                        queue.add(new pair(nx, ny, cur.dis+1));
                    }
                }
            }
        }
        return min;
    }
    static class pair {
        int x;
        int y;
        int dis;

        pair(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
}
