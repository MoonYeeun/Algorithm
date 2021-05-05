package boj;

import java.util.*;

// 플로이드 와샬 + 조합
class Back_21278 {
    static int[][] arr;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m = sc.nextInt();

        arr = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(arr[i], 100000);
            arr[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int b1 = sc.nextInt();
            int b2 = sc.nextInt();

            arr[b1][b2] = arr[b2][b1] = 1;
        }

        floyd();

        int ans1 = 0;
        int ans2 = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int result = cal(i, j);
                if (min > result) {
                    min = result;
                    ans1 = i;
                    ans2 = j;
                }
            }
        }
        System.out.println(ans1 + " " + ans2 + " " + min);
    }

    public static void floyd() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == k || j == k) continue;

                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }
    }

    public static int cal(int b1, int b2) {
        int total = 0;

        for (int i = 1; i <= n; i++) {
            total += (Math.min(arr[i][b1], arr[i][b2]) * 2);
        }

        return total;
    }
}

