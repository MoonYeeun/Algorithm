package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 11559 Puyo Puyo
/*
1. 깰 수 있는 그룹이 있는지 검사 (dfs)
2. 깰 그룹의 색깔을 . 로 바꿔줌
3. 나머지 블록 이동
4. 반복
*/
public class Back_11559 {
    static char[][] map = new char[12][6];
    static int[][] visit;
    static int cnt = 0;
    static int answer = 0;
    static Queue<pair> queue = new LinkedList<>();
    // 상하좌우 비교
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0 ; i < 12 ; i++) {
            String str = sc.next();
            for(int j = 0 ; j < 6 ; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        while(true){
            boolean isGroup = false;
            for(int i = 0 ; i < 12 ; i++) {
                for(int j = 0 ; j < 6 ; j++) {
                    cnt = 0;
                    if(map[i][j] != '.'){
                        queue.clear();
                        visit = new int[12][6];
                        dfs(i, j, map[i][j]);

                        if(cnt >= 4) {
                            isGroup = true;
                            colorChange();
                        }
                    }
                }
            }
            if(!isGroup) break;
            move();
        }
        System.out.println(answer);
    }
    // 깰 그룹의 색깔을 .로 바꿔줌
    static void colorChange() {
        while(!queue.isEmpty()){
            pair pair = queue.poll();
            map[pair.x][pair.y] = '.';
        }
    }
    // 블록 이동
    static void move() {
        for(int i = 0 ; i < 6 ; i++) {
            for(int j = 11 ; j >= 0 ; j--) {
                if(map[j][i] == '.') continue;
                char temp = map[j][i];
                map[j][i] = '.';
                int idx  = j;
                while (true) {
                    if(idx+1 >= 12 || map[idx+1][i] != '.') break;
                    idx++;
                }
                map[idx][i] = temp;
            }
        }
        answer++;
    }
    static void dfs(int x , int y, char color) {
        cnt++;
        visit[x][y] = 1;
        queue.add(new pair(x,y));
        for(int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 12 && nx >= 0 && ny < 6 && ny >= 0 && visit[nx][ny] != 1 &&
                    map[nx][ny] == color){
                dfs(nx,ny, color);
            }
        }
    }
    static class pair{
        int x;
        int y;
        pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
