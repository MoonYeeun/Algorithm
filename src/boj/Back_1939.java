package boj;

import java.util.*;

// 백준 1939 중량제한
// bfs + 이분탐색
// 메모리....
public class Back_1939 {
    static ArrayList<pair>[] bridge;
    static boolean[] visit;
    static Queue<Integer> queue = new LinkedList<>();
    static int f1, f2;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int min = Integer.MAX_VALUE;
        int max = 0;

        bridge = new ArrayList[n+1];
        visit = new boolean[n+1];
        for(int i = 1 ; i <= n ; i++) {
            bridge[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < m ; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int w = sc.nextInt();

            bridge[s].add(new pair(e, w));
            bridge[e].add(new pair(s, w));

            if(w > max) max = w;
            if(w < min) min = w;
        }
        f1 = sc.nextInt();
        f2 = sc.nextInt();

        int ans = 0;
        while (min <= max) {
            int mid = (min + max) / 2;
            Arrays.fill(visit, false);
            queue.clear();

            if(check(mid)) {
                ans = mid;
                min = mid + 1;
            }
            else max = mid - 1;
        }
        System.out.println(ans);
    }
    static boolean check(int w) {
        queue.add(f1); // 출발 공장
        visit[f1] = true;

        while (!queue.isEmpty()) {
            int f = queue.poll();

            if(f == f2) return true;

            for(pair p : bridge[f]) {
                // 해당 다리 지날 수 있는 경우
                if(p.w >= w && !visit[p.d]) {
                    visit[p.d] = true;
                    queue.add(p.d);
                }
            }
        }
        return false;
    }
    static class pair {
        int d, w;

        pair(int d, int w) {
            this.d = d;
            this.w = w;
        }
    }
}
