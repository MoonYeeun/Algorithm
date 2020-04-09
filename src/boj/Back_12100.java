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
        // 위
        if(dir == 0) {
            for(int i = 0 ; i < n ; i++) {
                for(int j = 1 ; j < n ; j++) {

                    if(map[j][i] == 0) continue;
                    int idx = j;

                    while (idx > 0) {
                        if((map[idx-1][i] != 0 && map[idx-1][i] != map[idx][i])
                                || check[idx-1][i]) break;

                        if(!check[idx-1][i] && map[idx-1][i] != 0 && map[idx-1][i] == map[idx][i]) {
                            check[idx-1][i] = true;
                            map[idx-1][i] += map[idx][i];
                            map[idx][i] = 0;
                            continue;
                        }
                        map[idx-1][i] = map[idx][i];
                        map[idx][i] = 0;
                        idx--;
                    }
                }
            }
        }
        // 아래
        if(dir == 1) {
            for(int i = 0 ; i < n ; i++) {
                for(int j = n-2 ; j >= 0 ; j--) {
                    if(map[j][i] == 0) continue;
                    int idx = j;

                    while (idx < n-1) {
                        if((map[idx+1][i] != 0 && map[idx+1][i] != map[idx][i])
                                || check[idx+1][i]) break;

                        if(!check[idx+1][i] && map[idx+1][i] != 0 && map[idx+1][i] == map[idx][i]) {
                            check[idx+1][i] = true;
                            map[idx+1][i] += map[idx][i];
                            map[idx][i] = 0;
                            continue;
                        }
                        map[idx+1][i] = map[idx][i];
                        map[idx][i] = 0;
                        idx++;
                    }
                }
            }
        }
        // 왼
        if(dir == 2) {
            for(int i = 0 ; i < n ; i++) {
                for(int j = 1 ; j < n ; j++) {

                    if(map[i][j] == 0) continue;
                    int idx = j;

                    while (idx > 0) {
                        if((map[i][idx-1] != 0 && map[i][idx-1] != map[i][idx])
                                || check[i][idx-1]) break;

                        if(!check[i][idx-1] && map[i][idx-1] != 0 && map[i][idx-1] == map[i][idx]) {
                            check[i][idx-1] = true;
                            map[i][idx-1] += map[i][idx];
                            map[i][idx] = 0;
                            continue;
                        }
                        map[i][idx-1] = map[i][idx];
                        map[i][idx] = 0;
                        idx--;
                    }
                }
            }
        }
        // 오
        if(dir == 3) {
            for(int i = 0 ; i < n ; i++) {
                for(int j = n-2 ; j >= 0 ; j--) {

                    if(map[i][j] == 0) continue;
                    int idx = j;

                    while (idx < n-1) {
                        if((map[i][idx+1] != 0 && map[i][idx+1] != map[i][idx])
                                || check[i][idx+1]) break;

                        if(!check[i][idx+1] && map[i][idx+1] != 0 && map[i][idx+1] == map[i][idx]) {
                            check[i][idx+1] = true;
                            map[i][idx+1] += map[i][idx];
                            map[i][idx] = 0;
                            continue;
                        }
                        map[i][idx+1] = map[i][idx];
                        map[i][idx] = 0;
                        idx++;
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
