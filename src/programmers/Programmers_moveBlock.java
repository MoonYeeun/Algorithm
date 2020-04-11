package programmers;

import java.util.*;

// 2020 카카오 블록이동하기
// 조금 복잡한 bfs
// 로봇이 상 하 좌 우 이동하는 경우
// 가로 방향일 때 회전, 세로 방향일 때 회전 구분
// visit 체크 시 방향에 따라 구분하기
public class Programmers_moveBlock {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[] rotate = {1, -1};
    static Queue<pair> queue = new LinkedList<>();
    static boolean visit[][][];
    static int answer = 0;
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}};

        int size = board.length;
        visit = new boolean[size][size][2];
        visit[0][0][0] = visit[0][1][0] = true;

        queue.add(new pair(0, 0, 0, 1, 0, 0));
        bfs(size, board);
        System.out.println(answer);

    }
    static void bfs(int n, int[][] board) {
        while (!queue.isEmpty()) {
            pair pair = queue.poll();

            if((pair.x1 == n-1 && pair.y1 == n-1) || (pair.x2 == n-1 && pair.y2 == n-1)) {
                answer = pair.cnt;
                break;
            }

            // 이동
            for(int i = 0 ; i < 4 ; i++) {
                int nx1 = pair.x1 + dx[i];
                int ny1 = pair.y1 + dy[i];
                int nx2 = pair.x2 + dx[i];
                int ny2 = pair.y2 + dy[i];

                if(!isRange(nx1, ny1, n) || !isRange(nx2, ny2, n)) continue;
                if(board[nx1][ny1] == 1 || board[nx2][ny2] == 1) continue;
                if(visit[nx1][ny1][pair.dir] && visit[nx2][ny2][pair.dir]) continue;

                visit[nx1][ny1][pair.dir] = visit[nx2][ny2][pair.dir] = true;
                queue.add(new pair(nx1, ny1, nx2, ny2, pair.dir, pair.cnt+1));
            }
            // 회전
            if(pair.dir == 0) {
                // 가로 방향
                for(int r : rotate) {
                    if(!isRange(pair.x1 + r, pair.y1, n) || !isRange(pair.x2 + r, pair.y2, n)) continue;
                    if(board[pair.x1 + r][pair.y1] == 1 || board[pair.x2 + r][pair.y2] == 1) continue;
                    if(visit[pair.x1][pair.y1][1] && visit[pair.x1 + r][pair.y1][1] &&
                            visit[pair.x2][pair.y2][1] && visit[pair.x2 + r][pair.y2][1]) continue;

                    visit[pair.x1][pair.y1][1] = visit[pair.x1 + r][pair.y1][1] = true;
                    visit[pair.x2][pair.y2][1] = visit[pair.x2 + r][pair.y2][1] =true;
                    queue.add(new pair(pair.x1, pair.y1, pair.x1 + r, pair.y1, 1, pair.cnt+1));
                    queue.add(new pair(pair.x2, pair.y2, pair.x2 + r, pair.y2, 1, pair.cnt+1));
                }
            }
            if(pair.dir == 1) {
                // 세로 방향
                for(int r : rotate) {
                    if(!isRange(pair.x1, pair.y1 + r, n) || !isRange(pair.x2, pair.y2 + r, n)) continue;
                    if(board[pair.x1][pair.y1 + r] == 1 || board[pair.x2][pair.y2 + r] == 1) continue;
                    if(visit[pair.x1][pair.y1][0] && visit[pair.x1][pair.y1 + r][0] &&
                            visit[pair.x2][pair.y2][0] && visit[pair.x2][pair.y2 + r][0]) continue;

                    visit[pair.x1][pair.y1][0] = visit[pair.x1][pair.y1 + r][0] = true;
                    visit[pair.x2][pair.y2][0] = visit[pair.x2][pair.y2 + r][0] =true;
                    queue.add(new pair(pair.x1, pair.y1, pair.x1, pair.y1 + r, 0, pair.cnt+1));
                    queue.add(new pair(pair.x2, pair.y2, pair.x2, pair.y2 + r, 0, pair.cnt+1));
                }
            }
        }
    }
    static boolean isRange(int x, int y, int n) {
        if(x < 0 || x >= n || y < 0 || y >= n)
            return false;
        return true;
    }
    static class pair {
        int x1, y1, x2, y2, dir, cnt;

        pair(int x1, int y1, int x2, int y2, int dir, int cnt) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
}
