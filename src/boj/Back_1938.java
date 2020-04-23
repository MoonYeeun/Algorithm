package boj;

import java.util.*;

// 백준 1938 통나무 옮기
// 중간 통나무 값만 저장 !
public class Back_1938 {
    static int n;
    static char[][] map;
    static boolean[][][] visit;
    static int[] dx = {1, -1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, 1, -1, -1, 1, -1, 1};
    static Queue<Log> queue = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        map = new char[n][n];
        visit = new boolean[n][n][2];
        ArrayList<Log> list = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            String str = sc.next();
            for(int j = 0 ; j < n ; j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'B')
                    list.add(new Log(i, j, 0, 0)); // 통나무 위치
            }
        }
        // 통나무 세로: 0 가로: 1
        int dir = list.get(0).y == list.get(1).y ? 0 : 1;
        // 통나무 위치 큐로 옮기기
        visit[list.get(1).x][list.get(1).y][dir] = true;
        queue.add(new Log(list.get(1).x, list.get(1).y, dir, 0));

        System.out.println(bfs());
    }
    static int bfs() {
        int answer = 0;
        while (!queue.isEmpty()) {
            Log log = queue.poll();

            if(check(log.x, log.y, log.dir)) {
                answer = log.cnt;
                break;
            }
            //이동
            for(int i = 0 ; i < 4 ; i++) {
                int nx = log.x + dx[i];
                int ny = log.y + dy[i];

                if(!isRange(nx, ny) || visit[nx][ny][log.dir] || map[nx][ny] == '1') continue;

                if(log.dir == 0) {
                    if(!isRange(nx-1, ny) || !isRange(nx+1, ny)
                            || map[nx-1][ny] == '1' || map[nx+1][ny] == '1') {
                        continue;
                    }
                }
                else {
                    if(!isRange(nx, ny-1) || !isRange(nx, ny+1)
                            || map[nx][ny-1] == '1' || map[nx][ny+1] == '1') {
                        continue;
                    }
                }
                visit[nx][ny][log.dir] = true;
                queue.add(new Log(nx, ny, log.dir,log.cnt +1));
            }
            // 회전
            boolean flag = true;
            for(int i = 0 ; i < 8 ; i++) {
                int nx = log.x + dx[i];
                int ny = log.y + dy[i];

                if(!isRange(nx, ny) || map[nx][ny] == '1') {
                    flag = false;
                    break;
                }
            }
            if(!flag || visit[log.x][log.y][1 - log.dir]) continue;

            visit[log.x][log.y][1 - log.dir] = true;
            queue.add(new Log(log.x, log.y,1 - log.dir, log.cnt + 1));
        }
        return answer;
    }
    static boolean check(int x, int y, int dir) {
        if(dir == 0) {
            if(map[x-1][y] == 'E' && map[x][y] == 'E' && map[x+1][y] == 'E')
                return true;
        }
        else {
            if(map[x][y-1] == 'E' && map[x][y] == 'E' && map[x][y+1] == 'E')
                return true;
        }
        return false;
    }
    static boolean isRange(int x, int y) {
        if(x < 0 || x >= n || y < 0 || y >= n)
            return false;
        return true;
    }
    static class Log {
        int x, y, dir, cnt;

        Log(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
}
