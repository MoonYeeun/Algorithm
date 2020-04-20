package boj;

import java.util.*;

// 백준 17142 연구소3
public class Back_17142 {
    static int n, m;
    static int answer = Integer.MAX_VALUE;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[] flag;
    static ArrayList<pair> virus = new ArrayList<>();
    static Queue<pair> queue = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][n];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                map[i][j] = sc.nextInt();

                if(map[i][j] == 2)
                    virus.add(new pair(i, j));
            }
        }
        if(virus.size() == 0) System.out.println(-1);
        if(check(map)) System.out.println(0);
        else {
            flag = new boolean[virus.size()];
            choose_virus(0, 0);
            System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
        }
    }
    static void spread_virus() {
        int cnt = 0;
        boolean[][] visit = new boolean[n][n];
        int[][] temp = new int[n][n];
        copy(temp);

        while (!queue.isEmpty()) {
            cnt++;
            int idx = 0;
            int size = queue.size();
            while (idx < size) {
                idx++;

                pair pair = queue.poll();
                visit[pair.x][pair.y] = true;

                for(int i = 0 ; i < 4 ; i++) {
                    int nx = pair.x + dx[i];
                    int ny = pair.y + dy[i];

                    if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                    if(map[nx][ny] == 1 || visit[nx][ny]) continue;

                    temp[nx][ny] = 2;
                    visit[nx][ny] = true;
                    queue.add(new pair(nx, ny));
                }
            }
            if(check(temp)) {
                answer = Math.min(answer, cnt);
                queue.clear();
                break;
            }
        }
    }
    static void choose_virus(int idx, int cnt) {
        if(cnt == m) {
            for(int i = 0 ; i < virus.size() ; i++) {
                if(!flag[i]) continue;
                queue.add(virus.get(i));
            }
            spread_virus();
            return;
        }
        if(idx >= virus.size()) return;
        // 해당 바이러스 고르는 경우
        flag[idx] = true;
        choose_virus(idx+1, cnt + 1);
        flag[idx] = false;
        // 안고르는 경우
        choose_virus(idx+1, cnt);
    }
    static boolean check(int[][] temp) {
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(temp[i][j] == 0) return false;
            }
        }
        return true;
    }
    static void copy(int[][] temp) {
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                temp[i][j] = map[i][j];
            }
        }
    }
    static class pair {
        int x, y;

        pair(int x, int y) {
            this.x = x;
            this.y =y;
        }
    }
}
