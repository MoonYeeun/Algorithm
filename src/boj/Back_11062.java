package boj;

import java.util.*;

// 백준 11062 카드게임
// dp
public class Back_11062 {
    static int[][][] dp = new int[2][1001][1001];
    static int[] card = new int[1001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();

            for (int i = 0; i < n; i++) {
                card[i] = sc.nextInt();

                Arrays.fill(dp[0][i], -1);
                Arrays.fill(dp[1][i], -1);
            }

            System.out.println(play(0, 0, n - 1));
        }
    }

    public static int play(int turn, int s, int e) {
        if (s == e) {
            if (turn == 0) return card[s];
            else return 0;
        }
        if (dp[turn][s][e] != -1) return dp[turn][s][e];

        if (turn == 0) dp[turn][s][e] = Math.max(card[s] + play(1, s + 1, e), card[e] + play(1, s, e - 1));
        else dp[turn][s][e] = Math.min(play(0, s + 1, e), play(0, s, e - 1));

        return dp[turn][s][e];
    }
}
