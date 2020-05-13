package boj;

import java.util.*;
// 백준 18808 스티커붙이기
// 1. 해당 스티커 붙일 수 있는 지 확인
// 2. 안될 경우 90, 180, 270 회전 후 확인
public class Back_18808 {
    static int n, m, r, c;
    static boolean[][] map;
    static int[][] sticker;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int k = sc.nextInt();

        map = new boolean[n][m]; // 스티커 붙이는 판
        for(int i = 0 ; i < k ; i++) {
            r = sc.nextInt();
            c = sc.nextInt();

            sticker = new int[r][c];
            for(int s_i = 0 ; s_i < r ; s_i++) {
                for(int s_j = 0 ; s_j < c ; s_j++) {
                    sticker[s_i][s_j] = sc.nextInt();
                }
            }
            // 0 , 90, 180, 270 4번 회전 수행
            int cnt = 0;
            while (cnt < 4) {
                if(attach()) break;
                rotate();
                cnt++;
            }
        }
        int answer = 0;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                if(map[i][j]) answer++;
            }
        }
        System.out.println(answer);
    }
    static boolean attach() {
        for(int i = 0 ; i <= n - r; i++) {
            for(int j = 0 ; j <= m - c; j++) {
                if(check(i, j)) return true;
            }
        }
        return false;
    }
    static boolean check(int x, int y) {
        for(int i = x ; i < x + r ; i++) {
            for(int j = y ; j < y + c ; j++) {
                if(sticker[i - x][j - y] != 1) continue;
                if(map[i][j]) return false;
            }
        }
        // 스티커 붙일 수 있는 경우 붙이기
        for(int i = x ; i < x + r ; i++) {
            for(int j = y ; j < y + c ; j++) {
                if(sticker[i - x][j - y] == 1)
                    map[i][j] = true;
            }
        }
        return true;
    }
    static void rotate() {
        int[][] temp = new int[c][r];

        for(int i = 0 ; i < c ; i++) {
            for(int j = 0 ; j < r ; j++) {
                temp[i][j] = sticker[r - 1 - j][i];
            }
        }
        sticker = Arrays.copyOf(temp, temp.length);
        r = sticker.length;
        c = sticker[0].length;
    }
}
