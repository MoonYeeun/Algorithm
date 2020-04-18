package boj;

import java.util.*;

// 백준 13023 ABCDE
// dfs
public class Back_13023 {
    static ArrayList<Integer> list[];
    static boolean visit[];
    static int answer = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        list = new ArrayList[n];
        visit = new boolean[n];
        for(int i = 0 ; i < n ; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < m ; i++) {
            int p1 = sc.nextInt();
            int p2 = sc.nextInt();

            list[p1].add(p2);
            list[p2].add(p1);
        }
        for(int i = 0 ; i < n ; i++) {
            if(!visit[i]) dfs(i, 1);
            if(answer == 1) break;
        }
        System.out.println(answer);
    }
    static void dfs(int idx, int cnt) {
        if(cnt == 5) {
            answer = 1;
            return;
        }
        visit[idx] = true;
        for(int i : list[idx]) {
            if(!visit[i]) dfs(i, cnt + 1);
        }
        visit[idx] = false;
    }
}
