package boj;

import java.util.*;

public class Back_1256 {
    static int[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        dp = new int[n+1][m+1];
        if(n + m < k) System.out.println(-1);
        else {

        }
    }
    static int make(int idx, int n_cnt, int m_cnt) {
        return 1;
    }
}
