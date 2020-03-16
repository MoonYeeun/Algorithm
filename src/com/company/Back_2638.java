package com.company;

import java.util.*;
// 백준 2638 치즈
/*
1. 외부공기(2) - 내부공기(0) 구분
2. 루프 돌면서 2번 이상 외부공기와 접촉한 치즈 구분(3으로 만듬)
3. 3인 부분 외부공기(2)로 만들고 그 부분부터 외부공기-내부공기 구분
4. 치즈 다 녹았는지 확인
5. 덜 녹았으면 다 녹을 때까지 1,2,3,4 반복
*/
public class Back_2638 {
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n,m;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 세로
        m = sc.nextInt(); // 가로

        map = new int[n+1][m+1];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        //외부 공기 - 내부 공기 구분
        outAir(0,0);

        int answer = 0;
        while (true) {
            if(isClear() == 0) break;
            checkCheeze();
            melt();
            answer++;
        }
        System.out.println(answer);
    }
    // 외부 공기 구분하는 함수
    static void outAir(int x, int y) {
        visit = new boolean[n][m];
        map[x][y] = 2;
        visit[x][y] = true;
        for(int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < n && ny >= 0 && ny < m
                    && map[nx][ny] == 0 && !visit[nx][ny]) {
                map[nx][ny] = 2;
                visit[nx][ny] = true;
                outAir(nx, ny);
            }
        }
    }
    // 녹일 치즈 구분
    static void checkCheeze() {
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                int cnt = 0;
                if(map[i][j] == 1) {
                    for(int k = 0 ; k < 4 ; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 2)
                            cnt++;
                    }
                    if(cnt >= 2) map[i][j] = 3;
                }
            }
        }
    }
    // 치즈 녹이기
    static void melt() {
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                if(map[i][j] == 3) {
                    map[i][j] = 2;
                    outAir(i, j);
                }
            }
        }
    }
    // 치즈가 다 녹았는지 확인
    static int isClear() {
        int cnt = 0;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                if(map[i][j] == 1) cnt++;
            }
        }
        return cnt;
    }
}
