package boj;

import java.util.*;

// 백준 17822 원판돌리기
public class Back_17822 {
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[] dir = {1, -1};
    static int n, m, x, d, k;
    static boolean[][] visit;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int t = sc.nextInt();

        map = new int[n][m];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        int answer = 0;
        for(int i = 0 ; i < t ; i++) {
            x = sc.nextInt();
            d = sc.nextInt();
            k = sc.nextInt();

            answer = remove(move());
        }
        System.out.println(answer);
    }
    static int[][] move() {
        int[][] arr = copy(map);
        for(int i = (x-1); i < n ; i +=x) {
            for(int j = 0 ; j < m ; j++) {
                int idx = d == 0 ? (j + (dir[d] * k)) % m : (j + m + (dir[d] * k)) % m;
                arr[i][idx] = map[i][j];
            }
        }
        return arr;
    }
    static int remove(int[][] arr) {
        visit = new boolean[n][m];
        map = copy(arr);
        int cnt = 0; // 인접한 것 지운 수
        int total = 0;
        int num = 0;

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                for(int k = 0 ; k < 4 ; k++) {
                    int nx = i + dx[k];
                    int ny = (j + m + dy[k]) % m;

                    if(nx < 0 || nx >= n || ny < 0 || ny >= m || visit[nx][ny] || arr[i][j] == -1) continue;
                    if(arr[i][j] == arr[nx][ny]) {
                        map[i][j] = -1;
                        map[nx][ny] = -1;
                        cnt++;
                    }
                }
                visit[i][j] = true;
                if(map[i][j] == -1) continue;
                total += map[i][j];
                num++;
            }
        }
        if(cnt == 0) {
            // 인접하면서 같은 수 없는 경우
            double avg = total * 1.0/ num;
            total = 0;
            for(int i = 0 ; i < n ; i++) {
                for(int j = 0 ; j < m ; j++) {
                    if(map[i][j] == -1) continue;
                    else if(map[i][j] < avg) map[i][j]++;
                    else if(map[i][j] > avg) map[i][j]--;
                    total += map[i][j];
                }
            }
        }
        return total;
    }
    static int[][] copy(int[][] map) {
        int[][] arr = new int[n+1][m+1];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                arr[i][j] = map[i][j];
            }
        }
        return arr;
    }
}
