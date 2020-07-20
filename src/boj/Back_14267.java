package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 14267 내리칭찬
// 트리, dfs
public class Back_14267 {
    static ArrayList<Integer> list[];
    static int[] praise;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        praise = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= N ; i++) {
            list[i] = new ArrayList<>();

            if(i == 1) {
                st.nextToken();
                continue;
            }

            list[Integer.parseInt(st.nextToken())].add(i);
        }

        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            praise[p] += cnt;
        }

        dfs(1, praise[1]);

        for(int i = 1 ; i <= N ; i++) {
            System.out.print(praise[i] + " ");
        }
        System.out.println();
    }
    static void dfs(int s, int award) {
        praise[s] += award;

        for(int i : list[s]) {
            dfs(i, praise[s]);
        }
    }
}
