package boj;

import java.util.*;

// 백준 2174 로봇 시뮬레이션
public class Back_2174 {
    static int[][] map;
    static int[][] visit; // 로봇 존재
    static int a,b;
    //남 서 북 동
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static ArrayList<pair> robot = new ArrayList<>(); // 로봇 위치 정보 담김
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt(); // 가로
        b = sc.nextInt(); // 세로
        int n = sc.nextInt();
        int m = sc.nextInt();

        String dir = "SWNE";
        map = new int[b+1][a+1];
        visit = new int[b+1][a+1];
        // 로봇
        for(int i = 1 ; i <= n ; i++) {
            int x = sc.nextInt();
            int y = (b+1) - sc.nextInt(); // 왼쪽 위 0, 0
            int d = dir.indexOf(sc.next());

            visit[y][x] = i;
            robot.add(new pair(x, y, d));
        }
        // 명령
        String str = "OK";
        for(int i = 0 ; i < m ; i++) {
            int r = sc.nextInt();
            String o = sc.next();
            int cnt = sc.nextInt();

            str = move(r, o, cnt);
            if(!str.equals("OK")) break;
        }
        System.out.println(str);
    }
    static String move(int r, String o, int cnt) {
        String str = "OK";
        boolean flag = true;

        pair bot = robot.get(r - 1); // 움직일 로봇
        int x = bot.x;
        int y = bot.y;
        int dir = bot.dir;
        visit[y][x] = 0;

        int nx = bot.x;
        int ny = bot.y;

        for(int j = 0 ; j < cnt ; j++) {
            if(o.equals("L")) {
                dir = --dir < 0 ? dir + 4 : dir;
            }
            else if(o.equals("R")) {
                dir = ++dir >= 4 ? dir % 4 : dir;
            }
            else {
                nx = x + dx[dir];
                ny = y + dy[dir];
            }
            // 벽 or 로봇에 부딪힌 경우
            if(nx < 1 || nx > a || ny < 1 || ny > b) {
                str = "Robot " + r + " crashes into the wall";
                flag = false;
                break;
            }
            if(visit[ny][nx] != 0) {
                str = "Robot " + r + " crashes into robot " + visit[ny][nx];
                flag = false;
                break;
            }
            x = nx;
            y = ny;
        }
        if(!flag) return str;
        visit[y][x] = r;
        robot.set(r-1, new pair(x, y, dir));

        return str;
    }
    static class pair {
        int x, y, dir;

        pair(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
