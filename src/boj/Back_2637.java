package boj;

import java.util.*;

// 백준 2637 장난감조립
// 위상정렬
// 기본부품부터 - 중간부붐 - 완제품 순으로 블록개수 더해가기
// block[i][j] = i를 만드는데 필요한 j의 개수
public class Back_2637 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] indegree = new int[n + 1]; // i로 들어오는 간선 수
        int[][] block = new int[n + 1][n + 1]; // i를 만드는데 필요한 j의 개수

        ArrayList<pair>[] list = new ArrayList[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int k = sc.nextInt();

            indegree[x]++;
            list[y].add(new pair(x, k));
        }

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                block[i][i] = 1;
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (pair p : list[cur]) {
                for (int i = 1; i <= n; i++) {
                    block[p.idx][i] += block[cur][i] * p.cnt;
                }

                indegree[p.idx]--;
                if (indegree[p.idx] == 0) queue.add(p.idx);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (block[i][i] == 1) System.out.println(i + " " + block[n][i]);
        }
    }

    static class pair {
        int idx, cnt;

        pair(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }
}
