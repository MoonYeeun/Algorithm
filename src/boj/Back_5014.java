package boj;

import java.util.*;

// 백준 5014 스타트링크
public class Back_5014 {
    static int f, s, g, u, d;
    static Queue<pair> queue = new LinkedList<>();
    static boolean[] visit;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        f = sc.nextInt();
        s = sc.nextInt();
        g = sc.nextInt();
        u = sc.nextInt();
        d = sc.nextInt();

        visit = new boolean[f+1];
        visit[s] = true;
        queue.add(new pair(s, 0));
        bfs();
    }
    static void bfs() {
        boolean flag = false;
        int[] dx = {u, -d};
        while (!queue.isEmpty()) {
            pair p = queue.poll();

            if(p.floor == g) {
                flag = true;
                System.out.println(p.cnt);
                break;
            }

            for(int i = 0 ; i < 2 ; i++) {
                int newFloor = p.floor + dx[i];

                if(newFloor < 1 || newFloor > g || visit[newFloor]) continue;

                visit[newFloor] = true;
                queue.add(new pair(newFloor, p.cnt + 1));
            }
        }
        if(!flag) System.out.println("use the stairs");
    }
    static class pair {
        int floor, cnt;

        pair(int floor, int cnt) {
            this.floor = floor;
            this.cnt = cnt;
        }
    }
}
