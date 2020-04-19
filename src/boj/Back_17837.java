package boj;
import java.util.*;

// 백준 17837 새로운게임2
// 시뮬레이션
public class Back_17837 {
    static int[][] map;
    static Stack<pair> stack[][];
    static ArrayList<pair> list = new ArrayList<>();
    // 오, 왼, 위, 아
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static int answer = -1;
    static int n, k;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        map = new int[n+1][n+1];
        stack = new Stack[n+1][n+1];

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                map[i][j] = sc.nextInt();
                stack[i][j] = new Stack<>();
            }
        }
        for(int i = 0 ; i < k ; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int dir = sc.nextInt();
            list.add(new pair(x, y, dir, i, 0));
            stack[x][y].add(new pair(x, y, dir, i, 0));
        }
        move();
        System.out.println(answer);
    }
    static void move() {
        int cnt = 0;
        boolean flag = false;
        ArrayList<pair> temp = new ArrayList<>();

        while (cnt < 1000) {
            cnt++;
            for(pair pair : list) {
                temp.clear();

                int nx = pair.x + dx[pair.dir];
                int ny = pair.y + dy[pair.dir];

                // 체스판 벗어나거나 파란색일 경우
                if(nx < 1 || nx > n || ny < 1 || ny > n || map[nx][ny] == 2) {

                    if(pair.dir == 1 || pair.dir == 2) pair.dir = 3 - pair.dir;
                    else pair.dir = 7 - pair.dir;

                    nx = pair.x + dx[pair.dir];
                    ny = pair.y + dy[pair.dir];

                    // 방향 바꿨을 때 체스판 벗어난 경우 or 파란색 -> 그대로
                    if(nx < 1 || nx > n || ny < 1 || ny > n || map[nx][ny] == 2)
                        continue;
                }

                // 해당 말보다 위에 있는 것 같이 움직이도록
                while (stack[pair.x][pair.y].size()-1 >= pair.stack_idx) {
                    temp.add(stack[pair.x][pair.y].pop());
                }
                // 이동하려는 칸에 말이 4개 이상 쌓이는 경우
                if(4 - stack[nx][ny].size() <= temp.size()) {
                    flag = true;
                    break;
                }
                int size = stack[nx][ny].size();
                // 빨간색
                if(map[nx][ny] == 1) {
                    for(int i = 0 ; i < temp.size() ; i++) {
                        pair p = list.get(temp.get(i).idx);
                        p.x = nx;
                        p.y = ny;
                        p.stack_idx = size;
                        stack[nx][ny].add(new pair(nx, ny, p.dir, p.idx, size++));
                    }
                    continue;
                }
                // 흰색
                for(int i = temp.size() - 1 ; i >= 0 ; i--) {
                    pair p = list.get(temp.get(i).idx);
                    p.x = nx;
                    p.y = ny;
                    p.stack_idx = size;
                    stack[nx][ny].add(new pair(nx, ny, p.dir, p.idx, size++));
                }
            }
            if(flag) break;
        }
        if(flag) answer = cnt;
    }
    static class pair {
        int x, y, dir, idx, stack_idx;

        pair(int x, int y, int dir, int idx, int stack_idx) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.idx = idx;
            this.stack_idx = stack_idx;
        }
    }
}
