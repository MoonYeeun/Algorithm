package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 6497 전력난
// 최소신장트리
public class Back_6497 {
    static int[] root;
    static PriorityQueue<pair> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            root = new int[n];
            pq = new PriorityQueue<>();

            for (int i = 0; i < n; i++) {
                root[i] = i;
            }

            int total = 0;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                total += z;
                pq.add(new pair(x, y, z));
            }

            System.out.println(total - kruskal(n));
        }
    }

    static int kruskal(int n) {
        int cnt = 0; // 선택된 간선의 개수
        int total = 0;

        while (cnt < n - 1) {
            pair p = pq.poll();

            if (union(p.v1, p.v2)) {
                cnt++;
                total += p.weight;
            }
        }
        return total;
    }

    static int find(int a) {
        if (root[a] == a) return a;
        return root[a] = find(root[a]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        root[rootA] = rootB;
        return true;
    }

    static class pair implements Comparable<pair> {
        int v1, v2, weight;

        pair(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(pair pair) {
            return this.weight - pair.weight;
        }
    }
}
