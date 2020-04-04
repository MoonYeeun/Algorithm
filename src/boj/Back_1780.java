package boj;

import java.util.Scanner;

// 백준 1780 종이의 개수
// 분할정복
public class Back_1780 {
    static int[][] map;
    static int[] answer = new int[3];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        map = new int[9][9];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                map[i][j] = sc.nextInt()+1;
            }
        }
        divide(0, 0, n);
        for(int num : answer) {
            System.out.println(num);
        }
    }
    static boolean check(int x, int y, int len) {
        int num = map[x][y];
        for(int i = x ; i < x + len ; i++) {
            for(int j = y ; j < y + len ; j++) {
                if(num != map[i][j]) return false;
            }
        }
        return true;
    }
    static void divide(int x, int y, int len) {
        if(check(x, y, len)) answer[map[x][y]]++;
        else {
            int new_paper = len/3;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    divide(x + (new_paper * i), y + (new_paper *  j), new_paper);
                }
            }
        }
    }
}
