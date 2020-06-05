package boj;

import java.util.*;

// 백준 17562 친구비
// 유니온파인드 (친구비가 적은 사람 루트로)
public class Back_17562 {
    static int[] root, cost;
    static int n, m, k;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        root = new int[n+1];
        Arrays.fill(root, -1);

        cost = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            cost[i] = sc.nextInt();
        }
        // 친구 형성
        for(int i = 0 ; i < m ; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            union(x, y);
        }
        // 친구 고르기
        int ans = 0;
        for(int i = 1 ; i <= n ; i++) {
            if(root[i] == -1) ans += cost[i];
        }
        if(ans > k) System.out.println("Oh no");
        else System.out.println(ans);
    }
    static int find(int x) {
        if(root[x] < 0) return x;
        else return root[x] = find(root[x]);
    }
    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;
        // 친구비가 더 적은 사람이 root가 되도록
        if(cost[x] > cost[y]) root[x] = y;
        else root[y] = x;
    }
}
