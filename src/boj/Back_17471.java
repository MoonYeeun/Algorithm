package boj;

import java.util.*;

// 백준 17471 게리맨더링
// 비트마스킹 - 모든 경우의 수
// dfs - 연결된 구역인지 판단
public class Back_17471 {
    static int[] map, temp;
    static ArrayList<Integer> list[]; // 연결된 구역
    static boolean[] visit;
    static int n; // 총 인구수
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        map = new int[n+1];
        list = new ArrayList[n+1];

        for(int i = 1 ; i <= n ; i++) {
            map[i] = sc.nextInt();
            list[i] = new ArrayList<>();
        }
        for(int i = 1 ; i <= n ; i++) {
            int num = sc.nextInt();
            for(int j = 0 ; j < num ; j++) {
                int area = sc.nextInt();
                list[i].add(area);
                list[area].add(i);
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i = 1 ; i < (1<<n) - 1 ; i++) {
            temp = new int[n+1];
            visit = new boolean[n+1];
            // 각 선거구 시작점
            int idx1 = -1;
            int idx2 = -1;

            for(int j = 0 ; j < n ; j++) {
                if((i & (1<<j)) == (1<<j)) {
                    temp[j+1] = 1;
                    idx1 = j+1;
                }
                else idx2 = j+1;
            }
            pair pair1 = dfs(idx1, 1);
            pair pair2 = dfs(idx2, 0);

            if(pair1.cnt + pair2.cnt == n)
                answer = Math.min(answer, Math.abs(pair1.total - pair2.total));
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
    static pair dfs(int idx, int mark) {
        visit[idx] = true;
        pair pair = new pair(1, map[idx]);

        for(int i = 0 ; i < n ; i++) {
            if(!list[idx].contains(i) || visit[i] || temp[i] != mark) continue;
            pair temp = dfs(i, mark);
            pair.total += temp.total;
            pair.cnt += temp.cnt;
        }
        return pair;
    }
    static class pair {
        int cnt, total;

        pair(int cnt, int total) {
            this.cnt = cnt;
            this.total = total;
        }
    }
}
