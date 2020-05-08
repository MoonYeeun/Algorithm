package programmers;

import java.util.*;

// 2018 카카오 프렌즈4블록
public class Programmers_friends4block {
    public static void main(String[] args) {
        String[] board = {"HGNHU", "CRSHV", "UKHVL", "MJHQB", "GSHOT", "MQMJJ", "AGJKK", "QULKK"};
        System.out.println(solution(8, 5, board));
    }
    static boolean check(char[][] board, int[][] visit, int m, int n){
        boolean flag = false;
        for(int i = 0 ; i < m-1; i++) {
            for(int j = 0 ; j < n-1; j++) {
                char c = board[i][j];

                if(c == '0') continue;
                if(c != board[i][j+1]) continue;
                if(c != board[i+1][j]) continue;
                if(c != board[i+1][j+1]) continue;

                visit[i][j] = visit[i+1][j] = visit[i][j+1] = visit[i+1][j+1] = 1;
                flag = true;
            }
        }
        return flag;
    }
    static int cal(int[][] visit, int m, int n) {
        int cnt = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0 ; j < n; j++) {
                if(visit[i][j] == 1) cnt++;
            }
        }
        return cnt;
    }
    static void move(int[][] visit, char[][] map, int m, int n) {
        Stack<Character> st = new Stack<>();
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                if(visit[j][i] == 0) st.push(map[j][i]);
                map[j][i] = '0';
            }
            int j = m-1;
            while (!st.isEmpty()) map[j--][i] = st.pop();
        }
    }
    static public int solution(int m, int n, String[] board) {
        int answer = 0;
        // 보드 맵 만들기
        char[][] map = new char[m][n];

        for(int i = 0 ; i < m ; i++) {
            for(int j = 0 ; j < n ; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        while (true) {
            int[][] visit = new int[m][n];

            if(!check(map, visit, m, n)) break; // 없앨 블록 체크

            answer += cal(visit, m, n);
            move(visit, map, m, n); // 블록 이동
        }
        return answer;
    }
}
