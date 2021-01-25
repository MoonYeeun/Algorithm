package boj;

import java.util.*;

// 백준 15684 사다리 조작
// 완탐 & 구현
// ⭐️ 사다리 고르기 - 백트래킹
public class Back_15684 {
    static int n, m, h;
    static int ans = Integer.MAX_VALUE;
    static boolean[][] ladder;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 세로선
        m = sc.nextInt(); // 가로선
        h = sc.nextInt(); // 놓을 수 있는 위치

        ladder = new boolean[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            ladder[a][b] = true;
        }

        choose(1, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static boolean move() {
        for (int i = 1; i <= n; i++) {
            int cur = i; // 가로 시작점

            for (int j = 1; j <= h; j++) {
                if (ladder[j][cur]) cur++;
                else if (ladder[j][cur - 1]) cur--;
            }

            if (cur != i) return false;
        }

        return true;
    }

    public static void choose(int idx, int cnt) {
        if (cnt == 4) return;

        if (move()) {
            ans = Math.min(ans, cnt);
            return;
        }

        for (int i = idx; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                if (ladder[i][j] || ladder[i][j - 1]) continue;

                ladder[i][j] = true;
                choose(i, cnt + 1);
                ladder[i][j] = false;
            }
        }
    }
}
