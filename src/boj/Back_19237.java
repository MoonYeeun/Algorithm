package boj;

import java.util.*;

// ⭐️ 백준 19237 어른상어
// * 주안점
// 1. 처음 상어의 방향 잘 입력받기
// 2. 상어 이동할 때 각 상어 별 방향 우선순위대로 이동
//      - 냄새 없는 곳 없는 경우 -> 다시 2번대로 돌면서 자신의 냄새 찾기
// 3. 번호 작은 상어부터 움직이기 -> 겹치는 상어 있을 때 번호 큰 상어 바로 없애기 가능
public class Back_19237 {
    static int n, m, k;
    static info[][] map;
    static Queue<pair> shark = new LinkedList<>();
    static int[][][] sharkDir;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        map = new info[n][n];
        pair[] temp = new pair[m+1];// 상어 정보 임시 저장

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                int cur = sc.nextInt();

                if(cur > 0) {
                    temp[cur] = new pair(cur, i, j, 0, 0);
                    map[i][j] = new info(cur, k);
                }
                else map[i][j] = new info(0, 0);
            }
        }
        // 각 상어 처음 방향
        for(int i = 1; i <= m ; i++) {
            temp[i].dir = sc.nextInt();
            shark.add(temp[i]);
        }
        // 상어 이동방향 우선순위
        sharkDir = new int[m+1][5][5];
        for(int i = 1 ; i <= m ; i++) {
            for(int j = 1 ; j <= 4 ; j++) {
                for (int k = 1; k <= 4; k++) {
                    sharkDir[i][j][k] = sc.nextInt();
                }
            }
        }
        move();
    }
    static void move() {
        Queue<pair> sharkTemp = new LinkedList<>();
        int Time = 0;

        while (Time++ <= 1000) {
            int size = shark.size();

            // 상어 1만 남은 경우
            if(size == 1) {
                System.out.println(shark.poll().cnt);
                return;
            }

            while (size-- > 0) {
                pair p = shark.poll();

                // 상어 이동 -> 냄새 없으면서 각 상어의 방향 우선순위별로 이동 가능한지 판별
                boolean flag = false;
                int nx = 0; int ny = 0;

                for(int i = 1 ; i <= 4 ; i++) {
                    nx = p.x + dx[sharkDir[p.idx][p.dir][i]];
                    ny = p.y + dy[sharkDir[p.idx][p.dir][i]];

                    if(nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny].time != 0) continue;

                    flag = true;
                    sharkTemp.add(new pair(p.idx, nx, ny, sharkDir[p.idx][p.dir][i], p.cnt + 1));
                    break;
                }
                // 가능한 곳 없는 경우
                if(!flag) {
                    for(int i = 1 ; i <= 4 ; i++) {
                        nx = p.x + dx[sharkDir[p.idx][p.dir][i]];
                        ny = p.y + dy[sharkDir[p.idx][p.dir][i]];

                        if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                        if(map[nx][ny].shark == p.idx) {
                            sharkTemp.add(new pair(p.idx, nx, ny, sharkDir[p.idx][p.dir][i], p.cnt + 1));
                            break;
                        }
                    }
                }
            }
            // 기존에 있는 냄새들 제거
            removeSmell();

            // 이동할 상어 위치 시키기 (idx 작은 상어부터)
            while (!sharkTemp.isEmpty()) {
                pair tmp = sharkTemp.poll();
                // 이미 그 자리에 다른 상어 위치한 경우 -> 밀려남
                if(map[tmp.x][tmp.y].time != 0 && map[tmp.x][tmp.y].shark != tmp.idx) {
                    continue;
                }
                map[tmp.x][tmp.y].time = k;
                map[tmp.x][tmp.y].shark = tmp.idx;

                shark.add(tmp);
            }
        }
        System.out.println(-1);
    }
    static void removeSmell() {
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(map[i][j].time > 0) map[i][j].time--;
                if(map[i][j].time == 0) map[i][j].shark = 0;
            }
        }
    }
    static class pair {
        int idx, x, y, dir, cnt;

        pair(int idx, int x, int y, int dir, int cnt) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
    static class info {
        int shark, time;

        info(int shark, int time) {
            this.shark = shark;
            this.time = time;
        }
    }
}
