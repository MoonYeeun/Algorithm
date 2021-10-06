package programmers;

// 프로그래머스 위클리챌린지 9 전력망을 둘로 나누기
// union-find
public class Programmers_weekly9 {
    static int[] root;

    public static void main(String[] args) {
        int n = 4;
        int[][] wires = {{1, 2}, {2, 3}, {3, 4}};
        int result = solution(n, wires);
        System.out.println(result);
    }

    public static int solution(int n, int[][] wires) {
        int answer = n;

        for (int i = 0; i < n - 1; i++) {
            root = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                root[j] = j;
            }

            for (int j = 0; j < n - 1; j++) {
                if (i == j) continue; // 하나씩 빼기

                int a = wires[j][0];
                int b = wires[j][1];

                union(a, b);
            }

            int parent = find(1);
            int cnt = 1;
            for (int j = 2; j <= n; j++) {
                if (find(j) == parent) cnt++;
            }

            answer = Math.min(answer, Math.abs(n - cnt - cnt));
        }
        return answer;
    }

    public static int find(int n) {
        if (root[n] == n) return n;
        return root[n] = find(root[n]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        root[a] = b;
    }
}
