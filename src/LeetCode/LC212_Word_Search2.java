package LeetCode;

import java.util.*;

// 212. Word Search II
public class LC212_Word_Search2 {
    static HashSet<String> prefix;
    static HashSet<String> word;
    static HashSet<String> ans;
    static boolean[][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        char[][] board = {{'o' , 'a' , 'a' , 'n'}, {'e' , 't' , 'a' , 'e'}, {'i' , 'h' , 'k' , 'r'}, {'i' , 'f' , 'l' , 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};

        List<String> lists = findWords(board, words);
        for (String str : lists) {
            System.out.println(str);
        }
    }

    public static List<String> findWords(char[][] board, String[] words) {
        List<String> list = new ArrayList<>();
        prefix = new HashSet<>();
        word = new HashSet<>();
        ans = new HashSet<>();
        visit = new boolean[board.length][board[0].length];

        calPrefix(words);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                visit[i][j] = true;
                backtracking(i, j, String.valueOf(board[i][j]), board);
                visit[i][j] = false;
            }
        }

        list.addAll(ans);
        return list;
    }

    public static void calPrefix(String[] words) {
        for (String str : words) {
            word.add(str);

            // 나올 수 있는 prefix 미리 계산
            for (int i = 1; i <= str.length(); i++) {
                prefix.add(str.substring(0, i));
            }
        }
    }

    public static void backtracking(int x, int y, String str, char[][] board) {
        if (!prefix.contains(str)) return;
        if (word.contains(str)) ans.add(str);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length || visit[nx][ny]) continue;

            visit[nx][ny] = true;
            backtracking(nx, ny, str + board[nx][ny], board);
            visit[nx][ny] = false;
        }
    }
}
