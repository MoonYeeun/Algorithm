package boj;

import java.util.ArrayList;
import java.util.Scanner;

// 백준 15683 감시
public class Back_15683 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static ArrayList<pair> list = new ArrayList<>();
    static ArrayList<String> com_dir = new ArrayList<>();
    static int n, m;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = sc.nextInt();

                if(map[i][j] != 0 && map[i][j] != 6)
                    list.add(new pair(i, j, map[i][j]));
            }
        }
        if(!list.isEmpty()) {
            chooseDir(0, "");
            watch();
            System.out.println(answer);
        }
        else System.out.println(blindSpot(map));
    }
    static void watch() {
        for(String str : com_dir) {
            String[] temp = str.split("/");

            int[][] arr = copy();
            for(int i = 0 ; i < temp.length ; i++) {
                pair pair = list.get(i);
                arr = move(Integer.parseInt(temp[i]), pair.cctv, arr, pair);
            }
            answer = Math.min(answer, blindSpot(arr));
        }
    }
    // 감시
    static int[][] move(int dir, int cctv, int[][] arr, pair pair) {
        int cnt = 0;

        if(cctv == 1) cnt = 1;
        if(cctv == 2 || cctv == 3) cnt = 2;
        if(cctv == 4) cnt = 3;
        if(cctv == 5) cnt = 4;

        for(int i = 0 ; i < cnt ; i++) {
            if(cctv == 5) dir = i;
            int x = pair.x;
            int y = pair.y;

            while (true) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) break;
                if(map[nx][ny] == 6 || map[nx][ny] == -1) break;

                arr[nx][ny] = -1;
                x = nx;
                y = ny;
            }
            if(cctv == 3 || cctv == 4) dir = (dir + 5) % 4;
            if(cctv == 2) dir = dir + 2;
        }
        return arr;
    }
    // 카메라 방향 조합 구하기
    static void chooseDir(int idx, String dir) {
        if(idx == list.size()) {
            com_dir.add(dir);
            return;
        }
        if(list.get(idx).cctv == 2) {
            for(int i = 0 ; i < 2 ; i++) {
                chooseDir(idx + 1, dir + i + "/");
            }
        }
        else {
            if(list.get(idx).cctv == 5)
                chooseDir(idx + 1, dir + 5 + "/");
            else {
                for(int i = 0 ; i < 4 ; i++) {
                    chooseDir(idx + 1, dir + i + "/");
                }
            }
        }
    }
    static int blindSpot(int[][] arr) {
        int cnt = 0;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                if(arr[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }
    static int[][] copy() {
        int[][] arr = new int[n][m];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                arr[i][j] = map[i][j];
            }
        }
        return arr;
    }
    static class pair {
        int x, y, cctv;

        pair(int x, int y, int cctv) {
            this.x = x;
            this.y = y;
            this.cctv = cctv;
        }
    }
}
