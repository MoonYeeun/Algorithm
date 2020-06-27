package boj;

import java.util.*;

// 백준 2001 보석줍기
// 현재 위치에서 cur -> 다음에 갈 다리 next
// 1. next 의 무게가 현재보다 작은 경우 : 안 감
// 2. next 의 무게가 현재보다 크거나 같을 때 :
//  - cur 에 보석있으면 줍고 가거나 / 안 줍고 가거나
public class Back_2001 {
    static int n, m, k, ans;
    static int[] jew;
    static ArrayList<pair> list[];
    static boolean[][] visit;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        jew = new int[n+1]; // 보석이 있는 섬
        list = new ArrayList[n+1]; // 각 섬과 연결된 다리 정보
        visit = new boolean[n+1][(1<<15)];

        int idx = 1; // 보석이 있을 수 있는 섬 14개 구분
        for(int i = 0 ; i < k ; i++) {
            int island = sc.nextInt();
            jew[island] = idx++;
        }

        for(int i = 1 ; i <= n ; i++) list[i] = new ArrayList<>();

        for(int i = 0 ; i < m ; i++) {
            int i1 = sc.nextInt();
            int i2 = sc.nextInt();
            int w = sc.nextInt();

            list[i1].add(new pair(i2, w));
            list[i2].add(new pair(i1, w));
        }
        check(1, 0, 0);
        System.out.println(ans);
    }
    static void check(int cur, int bit, int cnt) {
        if(cur == 1 && ans <= cnt) {
            // 1인 섬에 보석 있는데 안주웠을 경우 줍기
            if(jew[1] > 0 && ((bit & ((1<<jew[cur]))) == 0)) cnt++;
            ans = cnt;
        }

        visit[cur][bit] = true;
        for(pair p : list[cur]) {
            // 용량 초과하는 경우
            if (p.w < cnt) continue;
            // 지나가기
            if (!visit[p.vtx][bit]) check(p.vtx, bit, cnt);
            // 보석 선택
            if (jew[cur] > 0 && p.w >= cnt + 1 && !visit[p.vtx][bit | (1<<jew[cur])])
                check(p.vtx, bit | (1<<jew[cur]) , cnt + 1);
        }
    }
    static class pair {
        int vtx, w;

        pair(int vtx, int w) {
            this.vtx = vtx;
            this.w = w;
        }
    }
}
