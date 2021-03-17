package LeetCode;

// 980. Unique Paths III
public class LC980_Unique_Paths3 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int width, height, ans;
    static boolean[][] visit;

    public static void main(String[] args) {
        //int[][] grid = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        int[][] grid = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}};
        int result = uniquePathsIII(grid);
        System.out.println(result);
    }

    public static int uniquePathsIII(int[][] grid) {
        width = grid[0].length;
        height = grid.length;
        visit = new boolean[height][width];

        int cnt = 0; // 방문해야 할 지점 개수
        int x = 0;
        int y = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == 0) cnt++;
                if (grid[i][j] == 1) {
                    x = i;
                    y = j;
                }
            }
        }
        visit[x][y] = true;
        dfs(cnt, -1, x, y, grid);
        return ans;
    }

    public static void dfs(int cnt, int cur, int x, int y, int[][] grid) {
        if (grid[x][y] == 2) {
            if (cnt == cur) ans++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= height || ny < 0 || ny >= width) continue;
            if (grid[nx][ny] == -1 || visit[nx][ny]) continue;

            visit[nx][ny] = true;
            dfs(cnt, cur + 1, nx, ny, grid);
            visit[nx][ny] = false;
        }
    }
}
