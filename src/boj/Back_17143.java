package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 171743 낚시왕
// 상어 스피드 줄이기
public class Back_17143 {
    // 1. 위 2. 아래 3. 오른쪽 4. 왼
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};
    static int r, c, m, answer;
    static pair[][] map;
    static Queue<pair> queue = new LinkedList<>(); // 상어의 위치
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        m = sc.nextInt();

        map = new pair[r+1][c+1];

        for(int i = 0 ; i < m ; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int s = sc.nextInt(); // 속력
            int d = sc.nextInt(); // 방향
            int z = sc.nextInt(); // 크기

            map[x][y] = new pair(x, y, s, d, z);
            queue.add(new pair(x, y, s, d, z));
        }
        for(int i = 1 ; i <= c ; i++) {
            findShark(i);
            moveShark();
        }
        System.out.println(answer);
    }
    // 상어 찾기
    static void findShark(int y) {
        for(int i = 1 ; i <= r ; i++) {
            if(map[i][y] != null) {
                answer += map[i][y].z;
                map[i][y] = null;
                break;
            }
        }
    }
    // 상어 이동
    static void moveShark() {
        pair[][] new_map = new pair[r+1][c+1];

        while (!queue.isEmpty()) {
            pair pair = queue.poll();
            // 상어가 잡혀서 없는 경우
            if(map[pair.x][pair.y] == null) continue;

            int dir = pair.d; // 상어가 이동할 방향
            int x = pair.x;
            int y = pair.y;
            int s = 0;

            if(dir == 1 || dir == 2) {
                s = pair.s % (2 * (r - 1));
                while(s-- > 0) { // 남은 스피드가 0이 될때까지 이동
                    if(x + dx[dir] < 1 || x + dx[dir] > r) dir = 3 - dir;
                    x += dx[dir];
                }
            }
            if(dir == 3 || dir == 4) {
                s = pair.s % (2 * (c - 1));
                while(s-- > 0) { // 남은 스피드가 0이 될때까지 이동
                    if(y + dy[dir] < 1 || y + dy[dir] > c) dir = 7 - dir;
                    y += dy[dir];
                }
            }
            // 이동 후 그 자리에 다른 상어 있는 경우
            if(new_map[x][y] != null) {
                // 자리에 있는 상어가 더 큰 경우
                if(new_map[x][y].z > pair.z)  continue;
            }
            new_map[x][y] = new pair(x, y, pair.s, dir, pair.z);
        }

        for(int i = 1 ; i <= r ; i++) {
            for(int j = 1 ; j <= c ; j++) {
                map[i][j] = new_map[i][j];
                if(map[i][j] != null)
                    queue.add(new pair(i, j, map[i][j].s, map[i][j].d, map[i][j].z));
            }
        }
    }
    static class pair {
        int x, y, s, d, z;

        pair(int x, int y, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
