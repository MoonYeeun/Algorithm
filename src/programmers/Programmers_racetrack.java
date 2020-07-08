package programmers;

import java.util.*;

// 카카오 경주로건설
public class Programmers_racetrack {
    static Queue<pair> queue = new LinkedList<>();
    static int n;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] dp;
    public static void main(String[] args) {
        //int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
        //int[][] board = {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}};
        int[][] board = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0}
                ,{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0}, {0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};
//        int[][] board = {{0,0,0,0,0,0}, {0,1,1,1,1,0}, {0,0,1,0,0,0},{1,0,0,1,0,1}
//        ,{0,1,0,0,0,1},{0,0,0,0,0,0}};

        System.out.println(solution(board));
    }
    static int solution(int[][] board) {
        n = board.length;

        dp = new int[n][n];
        // dp 배열 초기화
        for(int i = 0 ; i < n ; i++) {
            Arrays.fill(dp[i], 100000000);
        }

        queue.add(new pair(0, 0, 0, 0));
        queue.add(new pair(0, 0, 2, 0));

        return bfs(board);
    }
    static int bfs(int[][] board) {
        int ans = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            pair p = queue.poll();

            if(p.x == n-1 && p.y == n-1) {
                ans = Math.min(dp[n-1][n-1], p.total);
            }

            if(dp[p.x][p.y] < p.total) continue;

            for(int i = 0 ; i < 4 ; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                int total = p.total;

                if(nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == 1) continue;

                // 비용계산
                if(p.dir == i) total += 100;
                else total += 600;

                if(dp[nx][ny] < total) continue;

                dp[nx][ny] = total;
                queue.add(new pair(nx, ny, i, total));
            }
        }
        return ans;
    }
    static class pair {
        int x, y, dir, total;

        pair(int x, int y, int dir, int total) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.total = total;
        }
    }
}
