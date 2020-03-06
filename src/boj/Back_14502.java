package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
1. 벽 놓을지 말지 선택
2. 바이러스 퍼지게 하기
3. 0 개수 세기 (안전영역 구하기)
*/
public class Back_14502 {
    static int[][] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int n,m;
    static int answer = 0;
    static ArrayList<pair> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 세로
        m = sc.nextInt(); // 가로
        map = new int[n][m];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j] == 2) list.add(new pair(i,j)); // 바이러스 위치 기억
            }
        }
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                if(map[i][j] == 0) {
                    // 벽 만들기
                    map[i][j] = 1;
                    makeWall(1);
                    map[i][j] = 0;
                }
            }
        }
        System.out.println(answer);
    }
    // 벽 만들기
    static void makeWall(int cnt) {
        if(cnt == 3) {
            bfs();
            return;
        }

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1; // 해당 칸에 벽 세우기
                    makeWall(cnt + 1);
                    map[i][j] = 0; //다시 허물기
                }
            }
        }
    }
    // 바이러스 퍼지기
    static void bfs() {
        Queue<pair> temp_queue = new LinkedList<>();
        for(pair pair : list) {
            temp_queue.add(new pair(pair.x, pair.y));
        }
        //바이러스를 위한 map 복사
        int[][] virus = new int[n][m];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                virus[i][j] = map[i][j];
            }
        }
        while(!temp_queue.isEmpty()) {
            pair pair = temp_queue.poll();

            for(int i = 0 ; i < 4 ; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];

                if(nx < n && nx >= 0 && ny < m && ny >= 0
                        && virus[nx][ny] == 0) {
                    virus[nx][ny] = 2;
                    temp_queue.add(new pair(nx,ny));
                }
            }
        }
        answer = Math.max(answer, safezone(virus));
    }
    // 안전 영역 구하기
    static int safezone(int[][] virus) {
        int cnt = 0;
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                if(virus[i][j] == 0)
                    cnt++;
            }
        }
        return cnt;
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
