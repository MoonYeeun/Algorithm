package not_solve_myself;

import java.util.*;

// 백준 1507 궁금한 민호
// 플로이드 와샬
// 1. 최단경로 맞는지 확인 -> 아니면 -1
// 2. 경로 최소 : i -> j 와 i-> k -> j 경로 값 같으면 거쳐가는 경우
public class Back_1507 {
    static int result, n;
    static int[][] load;
    static boolean[][] d;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        load = new int[n+1][n+1];
        d = new boolean[n+1][n+1];
        int ans = 0;

        for(int i = 1 ; i <= n ; i++) {
            Arrays.fill(d[i], true);

            for(int j = 1 ; j <= n ; j++) {
                load[i][j] = sc.nextInt();
            }
        }
        floyd();
        for(int i = 1 ; i <= n ; i++) {
            for(int j = i+1 ; j <= n ; j++) {
                if(d[i][j]) ans += load[i][j];
            }
        }
        if(result == -1) System.out.println(-1);
        else System.out.println(ans);
    }
    static void floyd() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j || j == k || i == k) continue;
                    // 최단경로 만들지 못할경우
                    if (load[i][j] > load[i][k] + load[k][j]) {
                        result = -1;
                        return;
                    }
                    // i -> j 와 K 를 거쳐가는 경우 비용 같을 때 -> 거쳐가야 최단경로
                    else if (load[i][j] == load[i][k] + load[k][j])
                        d[i][j] = false;
                }
            }
        }
    }
}
