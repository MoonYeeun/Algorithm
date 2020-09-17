package boj;

import java.util.*;

// 백준 2623 음악 프로그램
// 위상정렬
// 1. 들어오는 간선없는 정점 큐에 넣기
// 2. 큐 돌면서 해당 정점과 연결된 간선 제거
// 3. 제거 후 들어오는 간선 0인경우 큐에 넣기
// 4. 위 과정 반복 (이 때, 모든 경우 끝나기 전에 큐 빈 경우: 사이클 형성 되었다는 의미 -> 불가능 의미)
public class Back_2623 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Integer>[] list = new ArrayList[n + 1];
        int[] indegree = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int cnt = sc.nextInt();
            int prev = sc.nextInt();

            for (int j = 1; j < cnt; j++) {
                int cur = sc.nextInt();
                indegree[cur]++;

                list[prev].add(cur);
                prev = cur;
            }
        }

        int[] ans = new int[n];
        Queue<Integer> queue = new LinkedList<>();

        // 들어오는 간선 없는 것 큐에 넣음
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) queue.add(i);
        }
        // 위상 정렬
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            // 큐 빈 경우 -> 사이클 -> 정답 불가능
            if (queue.isEmpty()) {
                flag = false;
                break;
            }

            int cur = queue.poll();
            ans[i] = cur;

            for (int next : list[cur]) {
                if (--indegree[next] == 0) queue.add(next);
            }
        }
        if (!flag) System.out.println(0);
        else {
            for (int i = 0; i < n; i++) {
                System.out.println(ans[i]);
            }
        }
    }
}
