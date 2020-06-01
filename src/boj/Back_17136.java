package boj;

import java.util.*;

// 백준 17136 색종이붙이기
// dfs
// 1. 1인 부분 찾기
// 2. 5 4 3 2 1 종이 다 붙여보기
// 3. 마지막 지점 도달하면 최소값 갱신
public class Back_17136 {
    static int[][] map = new int[10][10];
    static int[] paper = {0, 0, 0, 0, 0, 0}; // 각 색종이 종이
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int i = 0 ; i < 10 ; i++) {
            for(int j = 0 ; j < 10 ; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        dfs(0, 0, 0);
        if(ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }
    static void dfs(int x, int y, int cnt) {
        // 1 찾을 때까지
        while (map[x][y] == 0) {
            if(++y >= 10) {
                if(++x >= 10) {
                    ans = Math.min(ans, cnt);
                    return;
                }
                y = 0;
            }
        }
        if(ans <= cnt) return;

        for(int i = 5 ; i > 0 ; i--) {
            // 조건 만족 x
            if(!check(x, y, i) || paper[i] >= 5)
                continue;

            // 색종이 붙이기
            paper[i]++;
            attach(x, y, i, 0);
            dfs(x, y, cnt + 1);

            // 다시 원상 복구
            paper[i]--;
            attach(x, y, i, 1);
        }
    }
    static void attach(int x, int y, int len, int state) {
        for(int i = x ; i < x + len ; i++) {
            for(int j = y ; j < y + len ; j++) {
                map[i][j] = state;
            }
        }
    }
    static boolean check(int x, int y, int len) {
        for(int i = x ; i < x + len ; i++) {
            for(int j = y ; j < y + len ; j++) {
                if(i >= 10 || j >= 10 || map[i][j] == 0) return false;
            }
        }
        return true;
    }
}
