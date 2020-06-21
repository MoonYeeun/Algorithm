package boj;

import java.util.*;

// 백준 2140 지뢰찾기
public class Back_2140 {
    static int n;
    static char[][] map;
    static int[] dx = {1, -1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, 1, -1, -1, 1, -1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new char[n][n];

        for(int i = 0 ; i < n ; i++) {
            String str = sc.next();

            for(int j = 0 ; j < n ; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int ans = (n-2) * (n-2);
        for(int i = 1 ; i < n-1 ; i++) {
            for(int j = 1 ; j < n-1 ; j++) {
                // 지뢰 존재 하는 경우 -> 가장자리 숫자 하나씩 제거 (지뢰찾음 의미)
                if(check(i, j)) remove(i, j);
                else ans--;
            }
        }
        // 최대 지뢰 개수 = 총 # 개수 - 지운 지뢰 개수
        if(n <= 2) System.out.println(0);
        else System.out.println(ans);
    }
    static boolean check(int x, int y) {
        for(int i = 0 ; i < 8 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(map[nx][ny] == '0') return false;
        }
        return true;
    }
    static void remove(int x, int y) {
        for(int i = 0 ; i < 8 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(map[nx][ny] == '#' || map[nx][ny] == '0') continue;

            int temp = map[nx][ny] - '0' - 1;
            map[nx][ny] = (char)(temp + '0');
        }
    }
}
