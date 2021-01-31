package boj;

import java.util.*;

// 백준 20056 마법사 상어와 파이어볼
// 구현
public class Back_20056 {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int n, m, k;
    static Queue<Pair> queue;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            queue.add(new Pair(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        System.out.println(move());
    }

    static int move() {
        int ans = 0;
        for (int i = 0; i < k; i++) {
            FireBall[][] map = new FireBall[n][n];

            while (!queue.isEmpty()) {
                Pair p = queue.poll();

                int nx = (p.r + dx[p.d] * p.s) % n;
                int ny = (p.c + dy[p.d] * p.s) % n;

                if (nx < 0) nx += n;
                if (ny < 0) ny += n;

                int even_dir = 0; // 짝수 방향 개수
                int odd_dir = 0; // 홀수 방향 개수
                int mass = p.m;
                int speed = p.s;
                int dir = p.d;

                if (dir % 2 == 0) even_dir++;
                else odd_dir++;

                if (map[nx][ny] == null) {
                    map[nx][ny] = new FireBall(mass, speed, 1, dir, even_dir, odd_dir);
                    continue;
                }

                FireBall cur = map[nx][ny];
                cur.m += mass;
                cur.s += speed;
                cur.cnt++;
                cur.odd_dir += odd_dir;
                cur.even_dir += even_dir;
            }
            ans = split(map);
        }
        return ans;
    }

    static int split(FireBall[][] map) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == null) continue;
                if (map[i][j].cnt == 1) {
                    queue.add(new Pair(i, j, map[i][j].m, map[i][j].s, map[i][j].dir));
                    total += map[i][j].m;
                    continue;
                }

                int m = map[i][j].m / 5;
                int s = map[i][j].s / map[i][j].cnt;

                if (m == 0) continue;

                int idx = map[i][j].even_dir == 0 || map[i][j].odd_dir == 0 ? 0 : 1;
                int cnt = 0;

                while (cnt++ < 4) {
                    queue.add(new Pair(i, j, m, s, idx));
                    total += m;
                    idx += 2;
                }
            }
        }
        return total;
    }

    static class Pair {
        int r, c, m, d, s;

        Pair(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.d = d;
            this.s = s;
        }
    }

    static class FireBall {
        int m, s, cnt, dir, even_dir, odd_dir;

        FireBall(int m, int s, int cnt, int dir, int even_dir, int odd_dir) {
            this.m = m;
            this.s = s;
            this.cnt = cnt;
            this.dir = dir;
            this.even_dir = even_dir;
            this.odd_dir = odd_dir;
        }
    }
}
