package boj;

import java.util.*;

// 백준 2239 스도쿠
// 백트래킹
public class Back_2239 {
    static int[][] map = new int[9][9];
    static boolean flag = false;
    static boolean[][] row = new boolean[9][10]; // 해당 행에 숫자 존재하는지
    static boolean[][] col = new boolean[9][10]; // 해당 열에 숫자 존재하는지
    static boolean[][] square = new boolean[9][10]; // 해당 칸에 숫자 존재하는지
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int i = 0 ; i < 9 ; i++) {
            String num = sc.next();
            for(int j = 0 ; j < 9 ; j++) {
                map[i][j] = num.charAt(j) - '0';

                if(map[i][j] == 0) continue;

                row[i][map[i][j]] = true;
                col[j][map[i][j]] = true;
                square[(i / 3) * 3 + (j / 3)][map[i][j]] = true;
            }
        }
        check(0);
    }
    static void check(int cnt) {
        int x = cnt / 9;
        int y = cnt % 9;

        if(cnt == 81) {
            print();
            flag = true;
            return;
        }
        if(flag) return;

        // 이미 숫자 골라진 경우
        if(map[x][y] != 0) check(cnt + 1);
        else { // 숫자 골라야 하는 경우
            for(int i = 1 ; i <= 9 ; i++) {
                if(!row[x][i] && !col[y][i] && !square[(x / 3) * 3 + (y / 3)][i]) {
                    row[x][i] = true;
                    col[y][i] = true;
                    square[(x / 3) * 3 + (y / 3)][i] = true;
                    map[x][y] = i;

                    check(cnt + 1);

                    row[x][i] = false;
                    col[y][i] = false;
                    square[(x / 3) * 3 + (y / 3)][i] = false;
                    map[x][y] = 0;
                }
            }
        }
    }
    static void print() {
        for(int i = 0 ; i < 9 ; i++) {
            for(int j = 0 ; j < 9 ; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
