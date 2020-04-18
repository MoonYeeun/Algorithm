package boj;

import java.util.*;

// 백준 1944 복제로봇
// S부터 K를 찾아간다.
// K를 만나면 해당 지점에서 로봇 복제 & 현재까지 거리 (간선) 우선순위 큐에 저장
// 위 과정 반복
// 큐에 넣어진 거리 짧은 순으로 움직인 횟수 구함 (mst)
public class Back_1944 {
    static int n, m, answer;
    static int[] root;
    static boolean[][][] visit;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<pair> queue = new LinkedList<>();
    static PriorityQueue<pair> pq = new PriorityQueue<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][n];
        root = new int[m+3];
        visit = new boolean[n][n][m+3]; // 복제된 로봇 돌아다니는 경우
        // S = 0 K 는 3부터 증가 벽 2 지나다닐 수 있는 길 1
        int idx = 3;
        for(int i = 0 ; i < n ; i++) {
            String str = sc.next();
            for(int j = 0 ; j < n ; j++) {
                char c = str.charAt(j);

                if(c == 'S') {
                    map[i][j] = 0;
                    visit[i][j][0] = true;
                    queue.add(new pair(i, j, 0, 0));
                }
                else if(c == 'K')
                    map[i][j] = idx++;
                else
                    map[i][j] = c - '0' + 1;
            }
        }
        bfs();
        for(int i = 0 ; i < idx ; i++) {
            root[i] = i;
        }
        kruskal();
        System.out.println(answer);
    }
    static void bfs() {
        while (!queue.isEmpty()) {
            pair pair = queue.poll();

            for(int i = 0 ; i < 4 ; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == 2 || visit[nx][ny][pair.c])
                    continue;

                visit[nx][ny][pair.c] = true;
                // K 발견한 경우
                if(map[nx][ny] >= 3) {
                    // 로봇 카피
                    queue.add(new pair(nx, ny, 0 , map[nx][ny]));
                    // 간선 저장 (정점1, 정점2, 거리, x)
                    pq.add(new pair(pair.c, map[nx][ny], pair.w + 1, 0));
                    visit[nx][ny][map[nx][ny]] = true;
                    continue;
                }
                queue.add(new pair(nx, ny, pair.w + 1, pair.c));
            }
        }
    }
    static void kruskal() {
        int cnt = 0;
        while (!pq.isEmpty()) {
            pair pair = pq.poll();

            if(cnt >= m) break;
            if(find(pair.x) != find(pair.y)) {
                union(pair.x, pair.y);
                answer += pair.w;
                cnt++;
            }
        }
        if(cnt != m) answer = -1;
    }
    static int find(int x) {
        if(root[x] == x)
            return x;
        return root[x] = find(root[x]);
    }
    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        root[x] = y;
    }
    static class pair implements Comparable<pair>{
        int x, y, w, c;

        pair(int x, int y, int w, int c) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.c = c;
        }
        @Override
        public int compareTo(pair pair) {
            return this.w - pair.w;
        }
    }
}
