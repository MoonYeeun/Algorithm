package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14890 경사로
public class Back_14890 {
    static int[][] map1, map2;
    static boolean[] slope;
    static int n, l;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        map1= new int[n][n]; // 가로 칸 비교위함
        map2= new int[n][n]; // 세로 칸 비교위함

        for(int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < n ; j++) {
                int num = Integer.parseInt(st.nextToken());
                map1[i][j] = num;
                map2[j][i] = num;
            }
        }
        int answer = 0;
        for(int i = 0 ; i < n ; i++) {
            //가로 탐색
            if(check(i, map1)) answer++;
            //세로 탐색
            if(check(i, map2)) answer++;
        }
        System.out.println(answer);
    }
    static boolean check(int a, int[][] map) {
        slope = new boolean[n]; // 경사로 설치 여부
        int h = map[a][0]; // 현재 높이
        int cnt = 1; // 칸의 개수
        int i = 1;
        while (i < n) {
            if(h == map[a][i]) {
                cnt++;
                i++;
                continue;
            }
            // 두 칸의 높이차 2 이상
            if(Math.abs(h - map[a][i]) > 1) return false;
            // 높이 낮아졌을 때
            if(h > map[a][i]) {
                // 현재 기준 l번째까지 같은 높이인지 판단
                for(int cur = i ; cur < i+l ; cur++) {
                    if(cur >= n || map[a][i] != map[a][cur] || slope[cur]) return false;
                    slope[cur] = true;
                }
                cnt = 1;
                h = map[a][i];
                i += l;
                continue;
            }
            // 높이 높아졌을 때
            if(h < map[a][i]) {
                if(cnt < l) return false;
                for(int cur = i - 1; cur > i - 1 - l ; cur--) {
                    if(slope[cur]) return false;
                }
                cnt = 1;
                h = map[a][i];
                i++;
            }
        }
        return true;
    }
}
