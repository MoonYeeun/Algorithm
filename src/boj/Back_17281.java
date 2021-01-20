package boj;

import java.util.*;

// 백준 17281 ⚾
// 구현
public class Back_17281 {
    static int ans = 0;
    static int[][] score;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        score = new int[n + 1][10];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 9; j++) {
                score[i][j] = sc.nextInt();
            }
        }
        makeOrder(1, "", 0);
        System.out.println(ans);
    }

    public static void makeOrder(int cur, String result, int bit) {
        if (cur == 10) {
            ans = Math.max(ans, play(result));
            return;
        }

        if (cur == 4) {
            makeOrder(cur + 1, result + "1", bit | (1 << 1));
            return;
        }

        for (int i = 2; i <= 9; i++) {
            if ((bit & (1 << i)) == (1 << i)) continue;

            makeOrder(cur + 1, result + i, bit | (1 << i));
        }
    }

    public static int play(String order) {
        int total = 0;
        int idx = 0; // 시작선수

        for (int i = 1; i <= n; i++) {
            int out = 0;
            int[] ground = new int[4];

            // 3아웃 될 때까지 이닝 유지
            while (out < 3) {
                int player = order.charAt(idx++ % 9) - '0';

                if (score[i][player] == 0) {
                    out++;
                    continue;
                }
                total += go(ground, score[i][player], player);
            }
        }
        return total;
    }

    public static int go(int[] ground, int cnt, int player) {
        int total = 0;

        ground[0] = player;

        for (int i = 3; i >= 0; i--) {
            if (ground[i] == 0) continue;

            if (i + cnt >= 4) total++;
            else ground[i + cnt] = ground[i];

            ground[i] = 0;
        }

        return total;
    }
}
