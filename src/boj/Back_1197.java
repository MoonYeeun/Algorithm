package boj;

import java.util.*;

// 백준 1197 최소 스패닝 트리
// 크루스칼 알고리즘 !
// 간선 중 가중치가 가장 낮은 간선 부터 시작
// 해당 간선의 정점들이 같은 그룹 인지 아닌지 비교 (union-find)
// 같은 그룹 아니라면 같은 그룹으로 만들고 간선 추가
// 간선이 정점 - 1 개가 될 때까지 위 과정 반복
public class Back_1197 {
    static int[] root = new int[10001];
    static int answer = 0;
    static PriorityQueue<Point> pq = new PriorityQueue<>(); // 가장 가중치 낮은 간선 파악
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        HashSet<Integer> set = new HashSet<>(); // 정점의 개수 파악

        for(int i = 0 ; i < e ; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int weight = sc.nextInt();

            set.add(v1);
            set.add(v2);
            pq.add(new Point(v1, v2, weight));
        }
        // 각 정점 루트 초기화
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            int num = it.next();
            root[num] = num;
        }
        // 크루스칼 알고리즘 적용
        kruskal(set.size());
        System.out.println(answer);
    }
    static void kruskal(int cnt) {
        int i = 0;
        while (i < cnt - 1) {
            Point point = pq.poll();

            if(find(point.v1) != find(point.v2)) {
                union(point.v1, point.v2);
                i++;
                answer += point.weight;
            }
        }
    }
    static int find(int x) {
        if(root[x] == x)
            return x;
        return root[x] = find(root[x]);
    }
    static void union(int x, int y) {
        x = root[x];
        y = root[y];

        root[x] = y;
    }
    static class Point implements Comparable<Point> {
        int v1, v2;
        int weight;

        Point(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
        @Override
        public int compareTo(Point point) {
            return this.weight - point.weight;
        }
    }
}
