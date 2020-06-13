package boj;

import java.io.*;
import java.util.*;;

// 백준 1956 운동
// 플로이드 와샬
public class Back_1956 {
    static final int MAX = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] dist = new int[V+1][V+1];
        for(int i = 1 ; i <= V ; i++) {
            Arrays.fill(dist[i], MAX);
        }

        int a, b, c;
        for(int i = 1 ; i <= E ; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            dist[a][b] = c;
        }
        // 플로이드 와샬 이용하여 최단거리 구하기
        for(int k = 1 ; k <= V ; k++) {
            for(int i = 1 ; i <= V ; i++) {
                for(int j = 1 ; j <= V ; j++) {
                    if(dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int ans = MAX;
        for(int i = 1 ; i <= V ; i++) {
            if(dist[i][i] < ans) {
                ans = dist[i][i];
            }
        }
        System.out.println(ans == MAX ? -1 : ans);
    }
}
