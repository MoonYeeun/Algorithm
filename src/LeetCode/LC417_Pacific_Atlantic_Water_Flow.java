package LeetCode;

import java.util.*;

// 417. Pacific Atlantic Water Flow
// bfs
public class LC417_Pacific_Atlantic_Water_Flow {
    static int row, col;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] pacVisit, atlVisit;
    static Queue<pair> pacQueue, atlQueue;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        row = heights.length;
        col = heights[0].length;

        pacVisit = new boolean[row][col];
        atlVisit = new boolean[row][col];
        pacQueue = new LinkedList<>();
        atlQueue = new LinkedList<>();

        // pacific에서 갈 수 있는 곳 체크
        setStart(0, 0, pacQueue, pacVisit);
        bfs(pacQueue, pacVisit, heights);

        // atlantic에서 갈 수 있는 곳 체크
        setStart(row - 1, col - 1, atlQueue, atlVisit);
        bfs(atlQueue, atlVisit, heights);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (pacVisit[i][j] && atlVisit[i][j]) {
                    ans.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }
        return ans;
    }

    public void setStart(int rowFix, int colFix, Queue<pair> queue, boolean[][] visit) {
        for (int i = 0; i < col; i++) {
            if (visit[rowFix][i]) continue;

            visit[rowFix][i] = true;
            queue.add(new pair(rowFix, i));
        }
        for (int i = 0; i < row; i++) {
            if (visit[i][colFix]) continue;

            visit[i][colFix] = true;
            queue.add(new pair(i, colFix));
        }
    }

    public void bfs(Queue<pair> queue, boolean[][] visit, int[][] heights) {
        while (!queue.isEmpty()) {
            pair p = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
                if (visit[nx][ny] || heights[p.x][p.y] > heights[nx][ny]) continue;

                visit[nx][ny] = true;
                queue.add(new pair(nx, ny));
            }
        }
    }

    class pair {
        int x;
        int y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
