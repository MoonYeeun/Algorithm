package boj;

import java.util.*;

// 백준 11404 플로이드
// 플로이드 와샬 알고리즘 : 그래프에서 모든 정점 쌍 사이의 최단 거리를 구해주는 알고리즘
public class Back_11404 {
    static final int INF = 9999999;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] map = new int[n+1][n+1];

        for(int i = 1; i <= n ; i++) {
            Arrays.fill(map[i], INF);
        }
        for(int i = 0 ; i < m ; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            map[a][b] = Math.min(map[a][b], c);
        }

        for(int k = 1 ; k <= n ; k++) {
            for(int i = 1 ; i <= n ; i++) {
                for(int j = 1 ; j <= n ; j++) {
                    if(i == j) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        for(int i = 1; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                if(map[i][j] == INF) System.out.print("0 ");
                else System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
