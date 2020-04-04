package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 2933 미네랄
// 시뮬레이션 -> 문제를 잘..제대로 읽자..!
public class Back_2933 {
    static int r, c, n, cnt;
    static char[][] map, temp;
    static int[] turn;
    static boolean[][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<pair> queue = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();

        map = new char[r][c];

        for(int i = 0 ; i < r ; i++) {
            String str = sc.next();
            for(int j = 0 ; j < c ; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] != '.') cnt++;
            }
        }
        n = sc.nextInt();
        turn = new int[n];
        for(int i = 0 ; i < n ;i++) {
            turn[i] = sc.nextInt();
            throw_stick(i, turn[i]);
        }
        print(map);
    }
    static void throw_stick(int idx, int high) {
        if(idx % 2 == 0) {
            for(int i = 0 ; i < c ; i++) {
                if(map[r - high][i] == 'x') {
                    map[r - high][i] = '.';
                    cnt--;
                    break;
                }
            }
        } else {
            for(int i = c-1 ; i >= 0 ; i--) {
                if(map[r - high][i] == 'x') {
                    map[r - high][i] = '.';
                    cnt--;
                    break;
                }
            }
        }
        // 새로운 클러스터 생긴 경우
        if(cnt != find_cluster()) {
            move();
            cnt = find_cluster();
        }
    }
    static int find_cluster() {
        int cnt = 0; //클러스터 개수
        temp = new char[r][c];
        copy(temp);
        visit = new boolean[r][c];
        for(int j = 0 ; j < c ; j++) {
            if(map[r-1][j] != 'x' || visit[r-1][j]) continue;

            cnt++;
            queue.add(new pair(r-1, j));
            visit[r-1][j] = true;
            temp[r-1][j] = 'C';

            while (!queue.isEmpty()) {
                pair pair = queue.poll();
                for(int i = 0; i < 4; i++) {
                    int nx = pair.x + dx[i];
                    int ny = pair.y + dy[i];

                    if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                    if(!visit[nx][ny] && map[nx][ny] != '.')  {
                        queue.add(new pair(nx, ny));
                        visit[nx][ny] = true;
                        temp[nx][ny] = 'C';
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
    static void move() {
        int h = Integer.MAX_VALUE;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (temp[i][j] != 'x') continue;
                // 내려갈 수 있는 칸 구하기
                int cnt = 0;
                for (int k = i + 1; k < r; k++) {
                    // 아랫칸이 같은 클러스터가 아니라면
                    if (temp[k][j] == 'C') break;
                    else if (temp[k][j] == 'x') {
                        cnt = h;
                        break;
                    }
                    else
                        cnt++;
                }
                h = Math.min(h, cnt);
            }
        }

        // h칸만큼 내린다.
        for (int i = r - 1; i >= 0; i--) {
            for (int j = 0; j < c; j++) {
                if (temp[i][j] == 'x') {
                    map[i][j] = '.';
                    map[i + h][j] = 'x';
                }
            }
        }
    }
    static void copy(char[][] temp){
        for(int i = 0 ; i < r ; i++) {
            for(int j = 0 ; j < c ; j++) {
                temp[i][j] = map[i][j];
            }
        }
    }
    static void print(char[][] map){
        for(int i = 0 ; i < r ; i++) {
            for(int j = 0 ; j < c ; j++) {
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
