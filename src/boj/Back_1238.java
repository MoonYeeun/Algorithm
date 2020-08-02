package boj;

import java.io.*;
import java.util.*;

// 백준 1238 파티
// 플로이드 와샬
public class Back_1238 {
    static final int MAX = 1000000;
    static int n, x, m;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];

        for(int i = 1 ; i <= n ; i++) {
            Arrays.fill(map[i], MAX);
            map[i][i] = 0;
        }

        for(int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            map[a][b] = time;
        }
        // 최단 거리 맵 만들기
        floyd();

        int ans = 0;
        for(int i = 1; i <= n ; i++) {
            int dis = map[i][x] + map[x][i];

            ans = Math.max(ans, dis);
        }
        System.out.println(ans);
    }
    static void floyd() {
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                for(int k = 1 ; k <= n ; k++) {
                    if(i == j || i == k) continue;
                    if(map[j][k] <= map[j][i] + map[i][k]) continue;

                    map[j][k] = map[j][i] + map[i][k];
                }
            }
        }
    }
}

