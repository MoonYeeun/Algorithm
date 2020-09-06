package boj;

import java.util.*;

// 백준 18513 샘물
// bfs
public class Back_18513 {
    static int n, k;
    static Queue<pair> queue = new LinkedList<>();
    static HashSet<Long> set = new HashSet<>();
    static int[] dx = {-1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        for (int i = 0; i < n; i++) {
            long water = sc.nextInt();
            queue.add(new pair(water, 0));
            set.add(water);
        }
        bfs();
    }

    static void bfs() {
        int cnt = 0;
        int total = 0;
        while (!queue.isEmpty()) {
            pair p = queue.poll();

            for (int i = 0; i < 2; i++) {
                long nx = p.loc + dx[i];

                if (set.contains(nx)) continue;

                set.add(nx);
                total += p.dis + 1;
                cnt++;
                queue.add(new pair(nx, p.dis + 1));

                if (cnt == k) {
                    System.out.println(total);
                    return;
                }
            }
        }
    }

    static class pair {
        long loc, dis;

        pair(long loc, long dis) {
            this.loc = loc;
            this.dis = dis;
        }
    }
}
