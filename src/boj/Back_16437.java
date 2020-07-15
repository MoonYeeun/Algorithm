package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준 16437 양 구출 작전
// dfs
public class Back_16437 {
    static ArrayList<Integer> list[];
    static int[] sheep;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        list = new ArrayList[n+1];
        for(int i = 1; i <= n ; i++) {
            list[i] = new ArrayList<>();
        }
        sheep = new int[n+1];

        for(int i = 2 ; i <= n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String kind = st.nextToken();
            int cnt = Integer.parseInt(st.nextToken());
            int island = Integer.parseInt(st.nextToken());

            list[island].add(i); // 단방향 트리로 만들기

            if(kind.equals("S")) sheep[i] = cnt;
            else sheep[i] = -cnt;
        }

        long ans = dfs(1);
        System.out.println(ans);
    }
    static long dfs(int s) {
        long num = sheep[s];

        for(int i : list[s]) {
            long result = dfs(i);
            // 양 > 늑대
            if(result > 0) num += result;
        }
        return num;
    }
}
