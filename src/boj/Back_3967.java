package boj;

import java.util.*;

// 백준 3967 매직스타
// 백트래킹 (어쩔땐 하드코딩이 답..일지도)
public class Back_3967 {
    static char[][] map;
    static ArrayList<pair> list;
    static int row = 5;
    static int col = 9;
    static int bit, total;
    static boolean flag;
    static String alpha = "ABCDEFGHIJKL";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        map = new char[row][col];
        list = new ArrayList<>(); // 빈 칸 채워야 할 위치

        for(int i = 0 ; i < row ; i++) {
            String str = sc.next();
            for(int j = 0 ; j < col ; j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] >= 'A' && map[i][j] <= 'L') {
                    bit |= (1<<(map[i][j] - 'A'));
                    total++;
                }
                if(map[i][j] == 'x') list.add(new pair(i, j));
            }
        }
        choose(bit, total, 0);
    }
    static void choose(int bit, int cnt, int idx) {
        if(flag) return;
        if(cnt == 12) {
            if(check()) {
                print();
                flag = true;
            }
            return;
        }
        if(idx >= list.size()) return;

        pair p = list.get(idx);
        for(int i = 0 ; i < 12 ; i++) {
            if((bit & (1<<i)) == (1<<i)) continue;

            map[p.x][p.y] = alpha.charAt(i);
            choose(bit | (1<<i), cnt + 1, idx + 1);
            map[p.x][p.y] = 'x';
        }
    }
    static boolean check() {
        if((map[0][4] - 'A' + map[1][3] - 'A' + map[2][2] - 'A' + map[3][1] - 'A') + 4 != 26) return false;
        if((map[1][1] - 'A' + map[1][3] - 'A' + map[1][5] - 'A' + map[1][7] - 'A') + 4 != 26) return false;
        if((map[1][1] - 'A' + map[2][2] - 'A' + map[3][3] - 'A' + map[4][4] - 'A') + 4 != 26) return false;
        if((map[4][4] - 'A' + map[3][5] - 'A' + map[2][6] - 'A' + map[1][7] - 'A') + 4 != 26) return false;
        if((map[3][1] - 'A' + map[3][3] - 'A' + map[3][5] - 'A' + map[3][7] - 'A') + 4 != 26) return false;
        if((map[3][7] - 'A' + map[2][6] - 'A' + map[1][5] - 'A' + map[0][4] - 'A') + 4 != 26) return false;

        return true;
    }
    static void print() {
        for(int i = 0 ; i < row ; i++) {
            for(int j = 0 ; j < col ; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
    static class pair {
        int x, y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
