package boj;

import java.util.*;

public class Back_1941 {
    static ArrayList<Integer> list = new ArrayList<>();
    static char[][] map; // 좌석 별 학생 자리
    //static char[] student; // 1 ~ 25 까지의 학생
    static boolean[][] check;
    static boolean[][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        map = new char[5][5];
        visit = new boolean[5][5];
        check = new boolean[5][5];
        //student = new char[25];
        int s = 0;
        for(int i = 0 ; i < 5 ; i++) {
            String str = sc.next();
            for(int j = 0 ; j < 5 ; j++) {
                map[i][j] = str.charAt(j);
                //student[s++] = map[i][j];
            }
        }
        com(0, 0, 0);
        System.out.println(answer);
    }
    static void com(int cnt, int num, int bit) {
        if(cnt == 6) {
            // 이다솜파가 4명 이상인 경우만 확인
            int s = 0;
            int x = 0; int y = 0;
            for(int i = 0 ; i < 25 ; i++) {
                if((bit & (1<<i)) == (1<<i) && map[i/5][i%5] == 'S') {
                    x = i/5;
                    y = i%5;
                    s++;
                }
            }
            if(s >= 4) {
                if(dfs(x, y) == 7) answer++;
            }
            return;
        }
        if(num >= 25) return;

        // 해당 번호 선택하는 경우
        check[num/5][num%5] = true;
        com(cnt + 1, num + 1, bit | (1<<num));
        check[num/5][num%5] = false;

        // 안하는 경우
        com(cnt, num, bit);
    }
    static int dfs(int x, int y) {
        int cnt = 1;
        for(int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
            if(visit[nx][ny] || !check[nx][ny]) continue;
            cnt += dfs(nx, ny);
        }
        return cnt;
    }
}
