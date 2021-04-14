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
        Arrays.fill(dp[1], Integer.MAX_VALUE);
        Arrays.fill(dp[0], Integer.MAX_VALUE);

        while (--n > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());

            list[f1].add(f2);
            list[f2].add(f1);
        }

        visit[1] = true;
        int ans = Math.min(find(0, 1), find(1, 1));
        System.out.println(ans);
    }

    static int find(int isEarly, int cur) {
        if (dp[isEarly][cur] != Integer.MAX_VALUE) return dp[isEarly][cur];

        int res = isEarly == 1 ? 1 : 0;

        for (int i : list[cur]) {
            if (visit[i]) continue;

            visit[i] = true;

            if (isEarly == 1) res += Math.min(find(0, i), find(1, i));
            else res += find(1, i);

            visit[i] = false;
        }

        return dp[isEarly][cur] = res == 0 ? res : Math.min(res, dp[isEarly][cur]);
    }
}
