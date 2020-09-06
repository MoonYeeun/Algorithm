package boj;

import java.util.*;

// 백준 2660 회장뽑기
// 플로이드 와샬
public class Back_2660 {
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], 100);
            map[i][i] = 0;
        }

        while (true) {
            int f1 = sc.nextInt();
            int f2 = sc.nextInt();

            if (f1 == -1 && f2 == -1) break;

            map[f1][f2] = map[f2][f1] = 1;
        }
        floyd(n);

        PriorityQueue<pair> candidate = new PriorityQueue<>();
        PriorityQueue<Integer> president = new PriorityQueue<>();
        int cnt = 0;

        for (int i = 1; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= n; j++) {
                if (map[i][j] > max) max = map[i][j];
            }
            candidate.add(new pair(i, max));
        }

        int score = candidate.peek().score;
        while (!candidate.isEmpty() && candidate.peek().score == score) {
            president.add(candidate.poll().friend);
            cnt++;
        }

        System.out.println(score + " " + cnt);
        while (!president.isEmpty()) {
            System.out.print(president.poll() + " ");
        }
    }

    static void floyd(int n) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (k == i || k == j) continue;
                    if (map[i][k] + map[k][j] < map[i][j]) map[i][j] = map[i][k] + map[k][j];
                }
            }
        }
    }

    static class pair implements Comparable<pair> {
        int friend, score;

        pair(int friend, int score) {
            this.friend = friend;
            this.score = score;
        }

        @Override
        public int compareTo(pair pair) {
            return this.score - pair.score;
        }
    }
}
