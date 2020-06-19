package boj;

import java.util.*;

// 백준 7682 틱택토
// 경우의 수 잘 따지기
public class Back_7682 {
    static char[][] map;
    static boolean flag;
    static final int N = 3;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String str = sc.next();

            if(str.equals("end")) break;

            map = new char[N][N];
            flag = false;
            int idx = 0;
            int x_cnt = 0; int o_cnt = 0; int remain = 0;

            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    map[i][j] = str.charAt(idx++);

                    if(map[i][j] == 'X') x_cnt++;
                    else if(map[i][j] == 'O') o_cnt++;
                    else remain++;
                }
            }

            // 말이 안되는 경우
            if(x_cnt < o_cnt || x_cnt - o_cnt > 1) {
                System.out.println("invalid");
                continue;
            }
            // 판이 다찼을 경우 : O 안성립하면 성공
            if(remain == 0) {
                if(check('O') == 0) flag = true;
            }
            else {
                // X 이겼을 때 : x 개수 = o 개수 + 1
                if(check('X') == 1 && x_cnt == o_cnt + 1) flag = true;
                // O가 이겼을 때 : o 개수 = x 개수
                else if(check('O') == 1 && x_cnt == o_cnt) flag = true;
            }

            if(flag) System.out.println("valid");
            else System.out.println("invalid");
        }
    }
    static int check(char c) {
        int cnt = 0;
       // 이긴지 판별
        for(int i = 0 ; i < N ; i++) {
            // 가로 체크
            if(map[i][0] == map[i][1] && map[i][1] == map[i][2] && map[i][0] == c) cnt++;
            // 세로 체크
            if(map[0][i] == map[1][i] && map[1][i] == map[2][i] && map[0][i] == c) cnt++;
        }
        // 대각선 체크
        if(map[0][0] == map[1][1] && map[1][1] == map[2][2] && map[0][0] == c) cnt++;
        if(map[0][2] == map[1][1] && map[1][1] == map[2][0] && map[0][2] == c) cnt++;

        return cnt;
    }
}
