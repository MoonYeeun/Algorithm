package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 1613 역사
// 플로이드 - 와샬
public class Back_1613 {
    static int[][] history;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        history = new int[n + 1][n + 1];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());

            history[h1][h2] = -1;
            history[h2][h1] = 1;
        }

        floyd(n);

        int s = Integer.parseInt(br.readLine());
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());

            System.out.println(history[h1][h2]);
        }

    }

    public static void floyd(int n) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j && history[i][j] != 0) continue;

                    if (history[i][k] == -1 && history[k][j] == -1) {
                        history[i][j] = -1;
                    } else if (history[i][k] == 1 && history[k][j] == 1) {
                        history[i][j] = 1;
                    }
                }
            }
        }
    }
}
