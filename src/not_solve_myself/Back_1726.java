package not_solve_myself;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
1. 출발점 / 도착점 / 방향 입력
2. Go 1,2,3 갈 수 있는지 확인
    1) 해당 방향으로 좌표를 이미 방문했다면 다음 칸 확인.
    2) 해당 방향으로 가는 좌표가 막혀있을 경우 이후 칸도 못 가기 때문에 반복문 탈출
    3) 해당 방향으로 갈 수 있는 좌표들은 큐에 넣기.
    4) 갈 수 있는 1, 2, 3칸 모두 횟수가 1번만 증가.
3. 바꿀 수 있는 방향 설정
4. 도착점 나올 때까지 반복
*/
public class Back_1726 {
    static int[][] map;
    static boolean[][][] visit;
    static pair start, end;
    static Queue<pair> queue = new LinkedList<>();
    static int m,n;
    // 1.동 2.서 3.남 4.북
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt(); // 세로
        n = sc.nextInt(); // 가로
        map = new int[m+1][n+1];
        visit = new boolean[m+1][n+1][5];

        for(int i = 1 ; i <= m ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        start = new pair(sc.nextInt(), sc.nextInt(), sc.nextInt(), 0); // 출발지점
        end = new pair(sc.nextInt(), sc.nextInt(), sc.nextInt(), 0); // 도착지점
        queue.add(start);
        System.out.println(bfs());
    }
    static int bfs() {
        while(!queue.isEmpty()) {
            pair pair = queue.poll();
            visit[pair.x][pair.y][pair.dir] = true;
            if(pair.x == end.x && pair.y == end.y && pair.dir == end.dir) return pair.cnt;
            // GO
            for(int i = 1 ; i <= 3 ; i++) {
                int nx = pair.x + dx[pair.dir-1] * i;
                int ny = pair.y + dy[pair.dir-1] * i;
                if (nx <= m && nx >= 1 && ny <= n && ny >= 1) {
                    if(visit[nx][ny][pair.dir]) continue;
                    if(map[nx][ny] == 0) {
                        visit[nx][ny][pair.dir] = true;
                        queue.add(new pair(nx, ny, pair.dir, pair.cnt+1));
                    } else break;
                }
            }
            // Turn
            for(int i = 1 ; i <= 4; i++) {
                if(pair.dir != i && !visit[pair.x][pair.y][i]) {
                    visit[pair.x][pair.y][i] = true;
                    if((pair.dir == 1 && i == 2) || (pair.dir == 2 && i == 1)
                            || (pair.dir == 3 && i == 4) || (pair.dir == 4 && i == 3))
                        queue.add(new pair(pair.x, pair.y, i, pair.cnt+2));
                    else queue.add(new pair(pair.x, pair.y, i, pair.cnt+1));
                }
            }
        }
        return -1;
    }
    static class pair {
        int x;
        int y;
        int dir;
        int cnt;

        pair(int x, int y, int dir, int cnt) {
            this.x = x; // 세로
            this.y = y; // 가로
            this.dir = dir; // 방향
            this.cnt = cnt; // 명령어 개수
        }
    }
}
