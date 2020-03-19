package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 17144 미세먼지 안녕!
public class Back_17144 {
    static int[][] map, temp;
    static int r, c, t;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[] cw = {2, 0, 3, 1}; // 시계방향
    static int[] ccw = {2, 1, 3, 0}; // 반시계방향
    static Queue<pair> dust = new LinkedList<>();
    static pair[] cleaner = new pair[2]; // 청소기 위치

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt(); // 행
        c = sc.nextInt(); // 열
        t = sc.nextInt();
        map = new int[r][c];
        int k = 0;
        for(int i = 0 ; i < r ; i++) {
            for(int j = 0 ; j < c ; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j] != 0 && map[i][j] != -1)
                    dust.add(new pair(i, j));
                if(map[i][j] == -1) {
                    cleaner[k] = new pair(i, j);
                    k++;
                }
            }
        }
        // 미세먼지 확산 & 공기 청청기 작동
        for(int i = 0 ; i < t ; i++) {
            temp = new int[r][c];
            spreadDust();
            addDust();
            operateCleaner();
            newDust();
        }
        // 남아있는 미세먼지 양 더하기
        int answer = 0;
        for(int i = 0 ; i < r ; i++) {
            for(int j = 0 ; j < c ; j++) {
                if(map[i][j] > 0)
                    answer += map[i][j];
            }
        }
        System.out.println(answer);
    }
    // 미세먼지 확산
    static void spreadDust() {
        while (!dust.isEmpty()) {
            pair pair = dust.poll();

            int vol = map[pair.r][pair.c] / 5;
            int cnt = 0; // 확산된 방의 개수
            for(int i = 0 ; i < 4 ; i++) {
                int nx = pair.r + dx[i];
                int ny = pair.c + dy[i];

                if(nx >= 0 && nx < r && ny >= 0 && ny < c && map[nx][ny] != -1 && vol > 0) {
                    temp[nx][ny] += vol;
                    cnt++;
                }
            }
            map[pair.r][pair.c] -= vol * cnt;
        }
    }
    // 각 방마다 확산된 미세먼지 더하기
    static void addDust() {
        for(int i = 0 ; i < r ; i++) {
            for(int j = 0 ; j < c ; j++) {
                map[i][j] += temp[i][j];
            }
        }
    }
    // 미세먼지 위치
    static void newDust() {
        for(int i = 0 ; i < r ; i++) {
            for(int j = 0 ; j < c ; j++) {
                if(map[i][j] != 0 && map[i][j] != -1)
                    dust.add(new pair(i, j));
            }
        }
    }
    // 공기청정기 작동
    static void operateCleaner() {
        pair c_top = cleaner[0];
        pair c_bottom = cleaner[1];
        int[][] copy = new int[r][c];
        // 원본 방 복사
        for(int i = 0 ; i < r ; i++) {
            for(int j = 0 ; j < c ; j++) {
                copy[i][j] = map[i][j];
            }
        }
        // 위쪽 청소기 -> 반시계
        int top_x = c_top.r;
        int top_y = c_top.c + 1;
        map[top_x][top_y] = 0;
        for(int i = 0 ; i < 4 ; i++) {
            while(true) {
                int nx = top_x + dx[ccw[i]];
                int ny = top_y + dy[ccw[i]];

                if(nx == c_top.r && ny == c_top.c) break;
                if(nx < 0 || nx >= r || ny < 0 || ny >= c) break;

                map[nx][ny] = copy[top_x][top_y];
                top_x = nx;
                top_y = ny;
            }
        }
        // 아래쪽 청소기 -> 시계
        int bottom_x = c_bottom.r;
        int bottom_y = c_bottom.c + 1;
        map[bottom_x][bottom_y] = 0;
        for(int i = 0 ; i < 4 ; i++) {
            while(true) {
                int nx = bottom_x + dx[cw[i]];
                int ny = bottom_y + dy[cw[i]];

                if(nx == c_bottom.r && ny == c_bottom.c) break;
                if(nx < 0 || nx >= r || ny < 0 || ny >= c) break;

                map[nx][ny] = copy[bottom_x][bottom_y];
                bottom_x = nx;
                bottom_y = ny;
            }
        }
    }
    static class pair{
        int r; // 행
        int c; // 열

        pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
