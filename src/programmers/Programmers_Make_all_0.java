package programmers;

import java.util.*;

// 프로그래머스 월간코드챌린지2 모두 0으로 만들기
// 위상정렬처럼 (리프노드부터 모두 더해가기)
public class Programmers_Make_all_0 {
    public static boolean[] visit;
    public static int[] connect;
    public static long[] weight;
    public static ArrayList<Integer>[] list;
    public static long ans;

    public static void main(String[] args) {
        int[] a = {-5, 0, 2, 1, 2};
        int[][] edges = {{0, 1}, {3, 4}, {2, 3}, {0, 3}};

        long result = solution(a, edges);
        System.out.println(result);
    }

    public static long solution(int[] a, int[][] edges) {
        connect = new int[a.length];
        weight = new long[a.length];
        visit = new boolean[a.length];
        list = new ArrayList[a.length];

        long total = 0;
        for (int i = 0; i < a.length; i++) {
            list[i] = new ArrayList<>();
            total += a[i];
            weight[i] = a[i];
        }
        if (total != 0) return -1;

        for (int[] edge : edges) {
            list[edge[0]].add(edge[1]);
            list[edge[1]].add(edge[0]);

            connect[edge[0]]++;
            connect[edge[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < connect.length; i++) {
            if (connect[i] == 1) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            visit[cur] = true;

            for (int i : list[cur]) {
                if (visit[i]) continue;

                weight[i] += weight[cur];
                connect[i]--;

                if (connect[i] == 1) queue.add(i);
            }
            ans += Math.abs(weight[cur]);
            weight[cur] = 0;
        }

        for (long i : weight) {
            if (i != 0) return -1;
        }
        return ans;
    }
}
