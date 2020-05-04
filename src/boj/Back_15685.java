package boj;

import java.util.*;

// 백준 15685 드래곤 커브
// 시뮬레이션 (규칙 잘 찾기..)
public class Back_15685 {
    static boolean[][] visit;
    static int[] dx = {0, -1, 0, 1, 0, 1};
    static int[] dy = {1, 0, -1, 0, 0, 1};
    static int[] check = {0, 3, 4, 5}; // 정사각형 체크
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        visit = new boolean[101][101];
        int answer = 0;
        for(int i = 0 ; i < n ; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();
            int g = sc.nextInt();

            ArrayList<Integer> list = make_curve(d, g); // 드래곤 커브 방향 설정
            draw_curve(y, x, list); // 해당 방향으로 이동 좌표 체크
        }
        for(int i = 0 ; i < 100 ; i++) {
            for(int j = 0 ; j < 100 ; j++) {
                boolean flag = true;
                for(int l = 0 ; l < 4 ; l++) {
                    if(!visit[i + dx[check[l]]][j + dy[check[l]]]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) answer++;
            }
        }
        System.out.println(answer);
    }
    static ArrayList<Integer> make_curve(int d, int g) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(d);

        for(int i = 1 ; i <= g ; i++) {
            int idx = list.size()-1;
            while (idx >= 0) {
                int dir = list.get(idx) + 1 == 4 ? 0 : list.get(idx) + 1;
                list.add(dir);
                idx--;
            }
        }
        return list;
    }
    static void draw_curve(int x, int y, ArrayList<Integer> list) {
        visit[x][y] = true;
        int nx, ny; // 세로 가로
        for(int i = 0 ; i < list.size() ; i++) {
            nx = x + dx[list.get(i)];
            ny = y + dy[list.get(i)];

            if(nx < 0 || nx > 100 || ny < 0 || ny > 100) continue;
            if(!visit[nx][ny]) visit[nx][ny] = true;
            x = nx;
            y = ny;
        }
    }
}
