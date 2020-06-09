package boj;

import java.util.*;

// 백준 3980 선발명단
public class Back_3980 {
    static int[][] pos;
    static int ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            pos = new int[11][11];
            ans = 0;

            for(int i = 0 ; i < 11 ; i++) {
                for(int j = 0 ; j < 11 ; j++) {
                    pos[i][j] = sc.nextInt();
                }
            }
            dfs(0, 0, 0);
            System.out.println(ans);
        }
    }
    static void dfs(int bit, int idx, int total) {
        // 다 선택된 경우
        if(bit == (1<<11) - 1) {
            ans = Math.max(ans, total);
        }

        for(int i = 0 ; i < 11 ; i++) {
            // 이미 선택한 포지션
            if((bit & (1<<i)) == (1<<i) || pos[idx][i] == 0) continue;

            dfs(bit | (1<<i), idx + 1, total + pos[idx][i]);
        }
    }
}
