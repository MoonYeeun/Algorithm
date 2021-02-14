package boj;

import java.util.*;

// 백준 17779 게리멘더링2
// 구현..
// 1. 기준점 설정
// 2. 구역 나눌 수 있는지 체크
// 3. 각 선거구 기준선에 따라 인구 계산
public class Back_17779 {
    static int n, total;
    static int[][] city;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        city = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                city[i][j] = sc.nextInt();
                total += city[i][j]; // 총 인구수
            }
        }
        System.out.println(divideCity());
    }

    public static int divideCity() {
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= n; j++) {
                for (int d1 = 1; d1 <= (j - 1); d1++) {
                    for (int d2 = 1; d2 <= (n - j); d2++) {
                        if (!canMakeBoundary(i, j, d1, d2)) continue;
                        ans = Math.min(ans, calculate(i, j, d1, d2));
                    }
                }
            }
        }
        return ans;
    }

    public static boolean canMakeBoundary(int x, int y, int d1, int d2) {
        if (x + d1 > n || y - d1 <= 0) return false;
        if (x + d2 > n || y + d2 > n) return false;
        if (x + d1 + d2 > n || y + d2 - d1 > n) return false;
        return x + d1 + d2 <= n && y + d2 - d1 > 0;
    }

    public static int calculate(int x, int y, int d1, int d2) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        int result = 0;
        int city5 = total;

        // 1번 선거구 인구수 계산
        int range = y;
        for (int i = 1; i < x + d1; i++) {
            if (i >= x) range--;
            for (int j = 1; j <= range; j++) {
                result += city[i][j];
            }
        }
        city5 -= result;
        max = Math.max(max, result);
        min = Math.min(min, result);

        // 2번 계산
        result = 0;
        range = y + 1;
        for (int i = 1; i <= x + d2; i++) {
            if (i > x) range++;
            for (int j = range; j <= n; j++) {
                result += city[i][j];
            }
        }
        city5 -= result;
        max = Math.max(max, result);
        min = Math.min(min, result);

        // 3번 계산
        result = 0;
        range = y - d1 + d2;
        for (int i = n; i >= x + d1; i--) {
            if (i < x + d2 + d1) range--;
            for (int j = 1; j < range; j++) {
                result += city[i][j];
            }
        }
        city5 -= result;
        max = Math.max(max, result);
        min = Math.min(min, result);

        // 4번 계산
        result = 0;
        range = y - d1 + d2;
        for (int i = n; i > x + d2; i--) {
            if (i <= x + d2 + d1) range++;
            for (int j = range; j <= n; j++) {
                result += city[i][j];
            }
        }
        city5 -= result;
        max = Math.max(max, result);
        min = Math.min(min, result);

        max = Math.max(max, city5);
        min = Math.min(min, city5);
        return max - min;
    }
}
