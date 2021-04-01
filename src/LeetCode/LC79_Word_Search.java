package LeetCode;

// 79. Word Search
public class LC79_Word_Search {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";

        boolean result = exist(board, word);
        System.out.println(result);
    }

    public static boolean exist(char[][] board, String word) {
        boolean flag = false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    flag = backtracking(board, i, j, -1, -1, "", word);
                }
            }
            if (flag) break;
        }
        return flag;
    }

    public static boolean backtracking(char[][] board, int x, int y, int prevX, int prevY, String cur, String word) {
        if (cur.equals(word)) return true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length) continue;
            if (nx == prevX && ny == prevY) continue;

            if (backtracking(board, nx, ny, x, y, cur + board[nx][ny], word)) return true;
        }

        return false;
    }
}
