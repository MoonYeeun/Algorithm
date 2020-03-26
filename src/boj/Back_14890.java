package boj;

import java.util.Scanner;

// 백준 14890 경사로
public class Back_14890 {
    static int[][] map;
    static boolean[] slope; // 경사로 놓은 자리
    static int n, l;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();
        map = new int[n][n];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int answer = 0;
        for(int i = 0 ; i < n ; i++) {
            //가로 탐색
            slope = new boolean[n];
            if(row_check(i)) answer++;
            //세로 탐색
            slope = new boolean[n];
            if(col_check(i)) answer++;
        }
        System.out.println(answer);
    }
    static boolean row_check(int a) {
        int h = map[a][0]; // 현재 높이
        int cnt = 1; // 같은 칸의 개수
        int i = 1;
        while (i < n) {
            if(h == map[a][i]) {
                cnt++;
                i++;
                continue;
            }
            // 두 칸의 높이차 2 이상
            if(Math.abs(h - map[a][i]) > 1) return false;
            // 높이 낮아졌을 때
            if(h > map[a][i]) {
                // 현재 기준 l번째까지 같은 높이인지 판단
                for(int cur = i ; cur < i+l ; cur++) {
                    if(cur >= n || map[a][i] != map[a][cur] || slope[cur]) return false;
                    slope[cur] = true;
                }
                cnt = 1;
                h = map[a][i];
                i += l;
                continue;
            }
            // 높이 높아졌을 때
            if(h < map[a][i]) {
                if(cnt < l) return false;
                // 지나온 길을 통해 경사로 놓을 수 있는지 판단
                for(int cur = i - 1; cur > i - 1 - l ; cur--) {
                    if(slope[cur]) return false;
                }
                cnt = 1;
                h = map[a][i];
                i++;
            }
        }
        return true;
    }
    static boolean col_check(int a) {
        int h = map[0][a];
        int cnt = 1;
        int i = 1;
        while (i < n) {
            if(h == map[i][a]) {
                cnt++;
                i++;
                continue;
            }
            // 두 칸의 높이차 2 이상
            if(Math.abs(h - map[i][a]) > 1) return false;
            // 높이 낮아졌을 때
            if(h > map[i][a]) {
                // 현재 기준 l번째까지 같은 높이인지 판단
                for(int cur = i ; cur < i+l ; cur++) {
                    if(cur >= n || map[i][a] != map[cur][a] || slope[cur]) return false;
                    slope[cur] = true;
                }
                cnt = 1;
                h = map[i][a];
                i += l;
                continue;
            }
            // 높이 높아졌을 때
            if(h < map[i][a]) {
                if(cnt < l) return false;
                for(int cur = i - 1; cur > i - 1 - l ; cur--) {
                    if(slope[cur]) return false;
                }
                cnt = 1;
                h = map[i][a];
                i++;
            }
        }
        return true;
    }
}
