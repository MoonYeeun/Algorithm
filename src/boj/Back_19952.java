package boj;

import java.util.*;

// 백준 19952 인성 문제 있어?
public class Back_19952 {
    static int w, h, obstacle, force, startX, startY, desX, desY;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<pair> queue;
    static boolean[][][] visit;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            h = sc.nextInt();
            w = sc.nextInt();
            obstacle = sc.nextInt();
            force = sc.nextInt();
            startX = sc.nextInt();
            startY = sc.nextInt();
            desX = sc.nextInt();
            desY = sc.nextInt();

            map = new int[h + 1][w + 1];
            visit = new boolean[h + 1][w + 1][force + 1];
            queue = new LinkedList<>();

            for (int i = 0; i < obstacle; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                map[x][y] = sc.nextInt();
            }

            queue.add(new pair(startX, startY, force));
            visit[startX][startY][force] = true;
            bfs();
        }
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            pair p = queue.poll();

            if (p.x == desX && p.y == desY) {
                System.out.println("잘했어!!");
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                int f = p.curForce;

                if (nx < 1 || nx > h || ny < 1 || ny > w || f <= 0) continue;

                // 점프해야 하는 경우
                if (map[p.x][p.y] < map[nx][ny] && f < map[nx][ny] - map[p.x][p.y]) continue;
                if (visit[nx][ny][--f]) continue;

                queue.add(new pair(nx, ny, f));
                visit[nx][ny][f] = true;
            }
        }
        System.out.println("인성 문제있어??");
    }

    static class pair {
        int x, y, curForce;

        pair(int x, int y, int curForce) {
            this.x = x;
            this.y = y;
            this.curForce = curForce;
        }
    }
}
