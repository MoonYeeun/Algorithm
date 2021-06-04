package boj;

import java.util.*;

// 백준 12851 숨바꼭질2
public class Back_12851 {
    static int n, k, count;
    static int ans = Integer.MAX_VALUE;
    static int MAX = 100001;
    static int[] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        visit = new int[MAX * 2 + 1];

        bfs(n, k);

        System.out.println(ans);
        System.out.println(count);
    }

    public static void bfs(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();
        int sec = 0;
        queue.add(n);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int cur = queue.poll();

                if (sec > ans) return;

                if (cur == k) {
                    ans = sec;
                    count++;
                    continue;
                }

                if (visit[cur] != 0 && visit[cur] < sec) {
                    continue;
                }

                visit[cur] = sec;
                if (cur < k) {
                    queue.offer(cur * 2);
                    queue.offer(cur + 1);
                }
                if (cur > 0) {
                    queue.offer(cur - 1);
                }
            }
            sec++;
        }
    }
}
