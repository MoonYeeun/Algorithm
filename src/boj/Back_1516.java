package boj;

import java.util.*;

// 백준 1516 게임 개발
// 위상정렬
public class Back_1516 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] cost = new int[n + 1];
        int[] indegree = new int[n + 1];
        int[] ans = new int[n + 1];
        ArrayList<Integer>[] list = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            cost[i] = sc.nextInt();

            while (true) {
                int prev = sc.nextInt();
                if (prev == -1) break;

                indegree[i]++;
                list[prev].add(i);
            }
        }
        // 들어오는 간선 0 인 것 큐에 넣기
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                ans[i] = cost[i];
            }
        }

        for (int i = 1; i <= n; i++) {
            int cur = queue.poll();

            for (int next : list[cur]) {
                // 최소 건설 시간 -> 방문해야하는 건물 다 완료되어야 지을 수 있으므로 최대값구함
                ans[next] = Math.max(ans[next], ans[cur] + cost[next]);
                if (--indegree[next] == 0) queue.add(next);
            }
        }
        // 결과
        for (int i = 1; i <= n; i++) {
            System.out.println(ans[i]);
        }
    }
}
