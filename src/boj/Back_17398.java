package boj;

import java.util.*;

//⭐️ 백준 17398 통신망 분할
// 유니온파온드 응용
// 최종 분할 상태 -> 연결해나가면 유니온파온드를 통해 해결 가능
public class Back_17398 {
    static int[] root;
    static long[] rank;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        root = new int[n+1];
        rank = new long[n+1];

        for(int i = 1; i <= n ; i++) {
            root[i] = i;
            rank[i] = 1;
        }

        // 연결 순서
        pair[] connect = new pair[m+1];
        for(int i = 1; i <= m ; i++) {
            connect[i] = new pair(sc.nextInt(), sc.nextInt());
        }
        // 합칠 순서 = 제거 순서 (역순)
        int[] remove = new int[q];
        boolean[] check = new boolean[m+1];

        for(int i = q-1 ; i >= 0 ; i--) {
            remove[i] = sc.nextInt();
            check[remove[i]] = true;
        }
        // 초기상태
        for(int i = 1 ; i <= m ; i++) {
            if(check[i]) continue;

            union(connect[i].x, connect[i].y); // 연결
        }
        // 제거의 역순으로 연결 (루트 같으면 이미 연결되어 있다는 뜻이므로 비용 0)
        long ans = 0;
        for(int i = 0 ; i < q ; i++) {
            int x = find(connect[remove[i]].x);
            int y = find(connect[remove[i]].y);

            ans += x == y ? 0 : rank[x] * rank[y];
            union(connect[remove[i]].x, connect[remove[i]].y);
        }
        System.out.println(ans);
    }
    static class pair {
        int x, y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;

        root[y] = x;
        rank[x]+= rank[y]; // 합칠 쪽에다 총 연결된 노드 수 저장
        rank[y] = 0;
    }
    static int find(int x) {
        if(root[x] == x) return x;

        return root[x] = find(root[x]);
    }
}
