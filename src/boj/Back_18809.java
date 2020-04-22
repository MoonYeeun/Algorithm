package boj;

import java.util.*;

// 백준 18809 Gaaaaaaaaarden
public class Back_18809 {
    static int n, m, r, g, answer;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[] sel;
    static ArrayList<pair> list = new ArrayList<>();
    static LinkedList<pair> temp = new LinkedList<>();
    static Queue<pair> queue = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        r = sc.nextInt();
        g = sc.nextInt();

        map = new int[n][m];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = sc.nextInt();

                if(map[i][j] == 2)
                    list.add(new pair(i, j, 0));
            }
        }
        sel = new int[list.size()];
        choose(0, 0, 0);
        System.out.println(answer);
    }
    static void bfs() {
        int[][][] visit = new int[n][m][3];
        boolean[][] flower = new boolean[n][m];
        int cnt = 0; // 꽃 피운 개수
        int time = 0; // 도착시간
        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;
            for(int j = 0 ; j < size ; j++) {
                pair pair = queue.poll();
                if(flower[pair.x][pair.y]) continue;
                if(visit[pair.x][pair.y][0] == 0)
                    visit[pair.x][pair.y][0] = -1; // 처음 도착시간

                for(int i = 0 ; i < 4 ; i++) {
                    int nx = pair.x + dx[i];
                    int ny = pair.y + dy[i];

                    if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    if(flower[nx][ny] || map[nx][ny] == 0) continue;
                    if(visit[nx][ny][0] != 0 && visit[nx][ny][0] != time) continue;

                    if(visit[nx][ny][pair.color] == 0) visit[nx][ny][pair.color]++;
                    visit[nx][ny][0] = time;
                    // 꽃 피울 수 있을 때
                    if(visit[nx][ny][pair.color] == 1 && visit[nx][ny][3 - pair.color] == 1) {
                        flower[nx][ny] = true; // 더이상 배양액 퍼트리지 않도록
                        cnt++;
                        continue;
                    }
                    queue.add(new pair(nx, ny, pair.color));
                }
            }
        }
        answer = Math.max(answer, cnt);
    }
    static void choose(int idx, int r_cnt, int g_cnt) {
        if(r_cnt == r && g_cnt == g) {
            for (int i = 0; i < list.size(); i++) {
                if(sel[i]==0) continue;
                pair pair = list.get(i);
                queue.add(new pair(pair.x, pair.y, sel[i]));
            }
            //queue.addAll(temp);
            bfs();
            return;
        }
        if(idx >= list.size()) return;

        pair pair = list.get(idx);
        // 안고르는 경우
        choose(idx + 1, r_cnt, g_cnt);
        // 빨간색 고르는 경우
        if(r_cnt < r) {
            //temp.add(new pair(pair.x, pair.y, 1));
            sel[idx] = 1;
            choose(idx + 1, r_cnt + 1, g_cnt);
            sel[idx] = 0;
        }
        // 초록색 고르는 경우
        if(g_cnt < g) {
            //temp.add(new pair(pair.x, pair.y, 2));
            sel[idx] = 2;
            choose(idx + 1, r_cnt, g_cnt + 1);
            //temp.pollLast();
            sel[idx] = 0;
        }
    }
    static class pair {
        int x, y, color;

        pair(int x, int y, int color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
}
