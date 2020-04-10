package boj;

import java.util.Scanner;

// 백준 12100 2048(easy)
public class Back_12100 {
    static int[][] map;
    static int n, answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        dfs(map, 0);
        System.out.println(answer);
    }
    static void dfs(int[][] map, int cnt) {
        if(cnt >= 5) {
            answer = Math.max(answer, find(map));
            return;
        }
        int[][] temp = new int[n][n];

        for(int i = 0 ; i < 4 ; i++) {
            copy(temp, map);
            move(i, temp);
            dfs(temp, cnt + 1);
        }
    }
    static void move(int dir, int[][] map) {
        boolean[][] check = new boolean[n][n];
        // 위 or 아래
        if(dir == 0 || dir == 1) {
            for(int i = 0 ; i < n ; i++) {
                for (int j = 1; j < n; j++) {
                    int idx = dir == 0 ? j : n - 1 - j;
                    int num = dir == 0 ? -1 : 1;

                    if(map[idx][i] == 0) continue;

                    while (idx > 0 || idx < n-1) {
                        if(dir == 0 && idx <= 0) break;
                        if(dir == 1 && idx > n-2) break;
                        if((map[idx + num][i] != 0 && map[idx + num][i] != map[idx][i])
                                || check[idx + num][i]) break;

                        if(map[idx + num][i] != 0 && map[idx + num][i] == map[idx][i]) {
                            check[idx + num][i] = true;
                            map[idx + num][i] += map[idx][i];
                            map[idx][i] = 0;
                            continue;
                        }
                        map[idx + num][i] = map[idx][i];
                        map[idx][i] = 0;
                        idx += num;
                    }
                }
            }
        }
        // 왼 or 오
        if(dir == 2 || dir == 3) {
            for(int i = 0 ; i < n ; i++) {
                for (int j = 1; j < n; j++) {
                    int idx = dir == 2 ? j : n - 1 - j;
                    int num = dir == 2 ? -1 : 1;

                    if(map[i][idx] == 0) continue;

                    while (idx > 0 || idx < n-1) {
                        if(dir == 2 && idx <= 0) break;
                        if(dir == 3 && idx > n-2) break;
                        if((map[i][idx + num] != 0 && map[i][idx + num] != map[i][idx])
                                || check[i][idx + num]) break;

                        if(map[i][idx + num] != 0 &&map[i][idx + num] == map[i][idx]) {
                            check[i][idx + num] = true;
                            map[i][idx + num] += map[i][idx];
                            map[i][idx] = 0;
                            continue;
                        }
                        map[i][idx + num] = map[i][idx];
                        map[i][idx] = 0;
                        idx += num;
                    }
                }
            }
        }
    }
    static void copy(int[][] temp, int[][] map) {
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                temp[i][j] = map[i][j];
            }
        }
    }
    static int find(int[][] map) {
        int max = 0;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
        return max;
    }
}
