package boj;

import java.util.*;

// 백준 14499 주사위굴리기
public class Back_14499 {
    static int[][] map;
    // 현재 밑면, 동, 북, 윗면, 서, 남
    static int[] dice = new int[6];
    static int[] move = {4, 1, 2, 5};
    // 1. 동 2. 서 3. 북 4. 남
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int k = sc.nextInt();

        map = new int[n][m];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        for(int i = 0 ; i < k ; i++) {
            int dir = sc.nextInt();

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

            roll_dice(dir);

            if(map[nx][ny] == 0) map[nx][ny] = dice[0];
            else {
                dice[0] = map[nx][ny];
                map[nx][ny] = 0;
            }
            x = nx;
            y = ny;

            System.out.println(dice[3]);
        }
    }
    static void roll_dice(int dir) {
        int[] temp = dice.clone();

        if(dir == 1) {
            dice[0] = temp[1];
            dice[3] = temp[4];
            dice[1] = temp[3];
            dice[4] = temp[0];
        }
        if(dir == 2) {
            dice[0] = temp[4];
            dice[3] = temp[1];
            dice[1] = temp[0];
            dice[4] = temp[3];
        }
        if(dir == 3) {
            dice[0] = temp[2];
            dice[3] = temp[5];
            dice[2] = temp[3];
            dice[5] = temp[0];
        }
        if(dir == 4) {
            dice[0] = temp[5];
            dice[3] = temp[2];
            dice[2] = temp[0];
            dice[5] = temp[3];
        }
    }
}
