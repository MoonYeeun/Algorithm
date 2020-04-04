package boj;

import java.util.Scanner;

// 백준 1992 쿼드트리
// 분할정복
public class Back_1992 {
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        map = new int[n][n];

        for(int i = 0 ; i < n ; i++) {
            String str = sc.next();
            for(int j = 0 ; j < n ; j++) {
                map[i][j] = (int)str.charAt(j) - '0';
            }
        }
        quad_tree(0, 0, n);
        System.out.println(sb.toString());
    }
    static int check(int x, int y, int size) {
        int num = map[x][y];
        for(int i = x ; i < x + size ; i++) {
            for(int j = y ; j < y + size ; j++) {
                if(map[i][j] != num) return 2;
            }
        }
        return num;
    }
    static void quad_tree(int x, int y, int size) {
        int num = check(x, y, size);
        if(num != 2) {
            sb.append(num);
            return;
        }
        if(size == 1) {
            sb.append(num);
            return;
        }
        else {
            sb.append("(");
            int new_size = size/2;
            quad_tree(x, y, new_size);
            quad_tree(x, y + new_size, new_size);
            quad_tree(x + new_size, y, new_size);
            quad_tree(x + new_size, y + new_size, new_size);
            sb.append(")");
        }
    }
}
