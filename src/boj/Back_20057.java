package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 20057 마법사 상어와 토네이도
/*
 * 1. 토네이도 이동 : 1칸씩부터 n칸까지 이동
 *                 같은 칸은 2번씩 이동 후 칸수 +1
 * 2. 토네이도 방향 : 왼 아래 오 위 순서로
 * 3. 모래 이동 : 각 방향별 계산해야하는 모래 위치 좌표화해두기 / 각 위치별 퍼센트도
 *              모래 위치가 좌표 밖을 벗어나는 경우 -> 격자 밖으로 나간 모래 계산
 * */
public class Back_20057 {
    static int n;
    static int[][] map;
    static int[] percentage = {1, 1, 2, 2, 5, 7, 7, 10, 10, 0};
    static int[][] sandX = {{-1, 1, -2, 2, 0, -1, 1, -1, 1, 0},
            {0, 0, 1, 1, 3, 1, 1, 2, 2, 2},
            {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0},
            {0, 0, -1, -1, -3, -1, -1, -2, -2, -2}};
    static int[][] sandY = {{0, 0, -1, -1, -3, -1, -1, -2, -2, -2},
            {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0},
            {0, 0, 1, 1, 3, 1, 1, 2, 2, 2},
            {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}};
    // 0 왼 1 아래 2 오른 3 위
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int mid = (1 + n) / 2;
        System.out.println(moveTornado(mid, mid));
    }

    static int moveTornado(int x, int y) {
        int cnt = 1;
        int dir = 0;
        int ans = 0;

        while (true) {
            for (int i = 0; i < 2; i++) {
                int temp = cnt;

                while (temp-- > 0) {
                    ans += flutterSand(x, y, (dir % 4));
                    x += dx[dir % 4];
                    y += dy[dir % 4];
                }
                dir++; // 방향 바꾸기
            }
            cnt++; // 이동 칸수 늘리기

            if (cnt == n) {
                for (int i = 0; i < cnt; i++) {
                    ans += flutterSand(x, y, (dir % 4));
                    x += dx[dir % 4];
                    y += dy[dir % 4];
                }
                break;
            }
        }
        return ans;
    }

    static int flutterSand(int x, int y, int dir) {
        int ans = 0;
        int moveX = x + dx[dir];
        int moveY = y + dy[dir];
        int total = map[moveX][moveY];
        int a = map[moveX][moveY]; // a로 이동한 모래

        for (int i = 0; i < 10; i++) {
            int nx = x + sandX[dir][i];
            int ny = y + sandY[dir][i];
            int amount = total * percentage[i] / 100;

            a -= amount;

            if (!check(nx, ny)) {
                ans += i != 9 ? amount : a;
                continue;
            }

            if (i == 9) map[nx][ny] += a;  // a로 이동한 모래
            else map[nx][ny] += amount;
        }

        map[moveX][moveY] = 0;
        return ans;
    }

    static boolean check(int x, int y) {
        return x >= 1 && x <= n && y >= 1 && y <= n;
    }
}
