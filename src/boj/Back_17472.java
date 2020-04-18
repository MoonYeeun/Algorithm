package boj;

import java.util.*;

// 백준 17472 다리만들기2
// 최소신장트리
// 1. 각 섬 구별
// 2. 각 섬 연결하는 다리 만들기
// 3. 다리 최소가 되는 거리 계산
public class Back_17472 {
    static int[][] map, arr;
    static boolean[][] visit;
    static int n, m, size, answer;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[] root;
    static Queue<pair> queue = new LinkedList<>(); // 각 섬 구별
    static Queue<pair> bridge_queue = new LinkedList<>(); // 다리 만들기
    static PriorityQueue<Point> pq = new PriorityQueue(); // 최소 간선 구하기
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        arr = new int[n][m];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = sc.nextInt();
                arr[i][j] = map[i][j];
            }
        }
        // 각 섬 구별
        int mark = 2;
        visit = new boolean[n][m];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                if(map[i][j] == 1 && !visit[i][j]) {
                    visit[i][j] = true;
                    arr[i][j] = mark;
                    queue.add(new pair(i, j));
                    divide_island(mark++);
                }
            }
        }
        // 다리 만들기
        make_brdige();
        root = new int[mark];
        size = mark - 2; // 섬의 개수
        for(int i = 2; i < mark ; i++) {
            root[i] = i;
        }
        // 최소 거리 구하기 (mst)
        cal_distance();
        System.out.println(answer);
    }
    static void divide_island(int mark) {
        while (!queue.isEmpty()) {
            pair pair = queue.poll();
            bridge_queue.add(new pair(pair.x, pair.y)); // 다리 만들기 위한 섬 좌표 저장

            for(int i = 0 ; i < 4 ; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m || visit[nx][ny]) continue;
                if(map[pair.x][pair.y] == map[nx][ny]) {
                    arr[nx][ny] = mark;
                    visit[nx][ny] = true;
                    queue.add(new pair(nx, ny));
                }
            }
        }
    }
    static void make_brdige() {
        while (!bridge_queue.isEmpty()) {
            pair pair = bridge_queue.poll();

            for(int i = 0 ; i < 4 ; i++) {
                int x = pair.x;
                int y = pair.y;
                int cnt = 0;

                while (true) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(nx < 0 || nx >= n || ny < 0 || ny >= m || arr[nx][ny] == arr[pair.x][pair.y])
                        break;

                    if(arr[nx][ny] != 0 && arr[nx][ny] != arr[pair.x][pair.y]) {
                        if(cnt >= 2)
                            pq.add(new Point(new pair(arr[pair.x][pair.y], arr[nx][ny]), cnt));
                        break;
                    }
                    x = nx;
                    y = ny;
                    cnt++;
                }
            }
        }
    }
    static void cal_distance() {
        int cnt = 0;
        while (!pq.isEmpty()) {
            if(cnt >= size - 1) break;

            Point point = pq.poll();
            if(find(point.pair.x) != find(point.pair.y)) {
                union(point.pair.x, point.pair.y);
                answer += point.weight;
                cnt++;
            }
        }
        if(cnt != size - 1) answer = -1;
    }
    static int find(int x) {
        if(root[x] == x) return x;
        return root[x] = find(root[x]);
    }
    static void union(int x, int y) {
        x = root[x];
        y = root[y];

        root[x] = y;
    }
    static class pair {
        int x, y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Point implements Comparable<Point>{
        pair pair;
        int weight;

        Point(pair pair, int weight) {
            this.pair = pair;
            this.weight = weight;
        }
        @Override
        public int compareTo(Point point) {
            return this.weight - point.weight;
        }
    }
}
