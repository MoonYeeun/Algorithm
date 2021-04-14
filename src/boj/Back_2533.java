package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 2533 사회망 서비스
// tree + dp
public class Back_2533 {
    static ArrayList<Integer>[] list;
    static int[][] dp;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        list = new ArrayList[n + 1];
        dp = new int[2][n + 1];
        visit = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        while (--n > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());

            list[f1].add(f2);
            list[f2].add(f1);
        }

        dfs(1);
        System.out.println(Math.min(dp[0][1], dp[1][1]));
    }

    static void dfs(int root) {
        visit[root] = true;

        dp[0][root] = 0;
        dp[1][root] = 1;

        for (int i : list[root]) {
            if (visit[i]) continue;

            dfs(i);
            dp[0][root] += dp[1][i];
            dp[1][root] += Math.min(dp[0][i], dp[1][i]);
        }
    }
}
