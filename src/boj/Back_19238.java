package boj;

import java.util.*;

// 백준 19238 스타트택시
// 각 승객의 출발지는 다 다르지만 목적지는 같을 수 있음 유의 !!!!!
public class Back_19238 {
    static int n, m, fuel;
    static pair taxi;
    static int[][] road;
    static pair[] des;
    static boolean flag; // 이동가능한지 판단
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        fuel = sc.nextInt();

        road = new int[n][n];
        des = new pair[m+2]; // 각 승객의 목적지 정보

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                road[i][j] = sc.nextInt();
            }
        }
        // 택시 초기 위치
        taxi = new pair(sc.nextInt()-1, sc.nextInt()-1);

        // 승객, 목적지 위치
        for(int i = 2 ; i < m + 2 ; i++) {
            int x = sc.nextInt()-1;
            int y = sc.nextInt()-1;
            int desX = sc.nextInt()-1;
            int desY = sc.nextInt()-1;

            road[x][y] = i; // 맵에 승객 위치 표시
            des[i] = new pair(desX, desY);
        }
        startTaxi();
    }
    static void startTaxi() {
        int size = m;
        // 태워야 하는 승객만큼 루프 돌기
        while (size-- > 0) {
            // 승객 찾기
            flag = false;
            int p = find();

            // 이동 불가능한 경우
            if(p == -1 || !flag) {
                System.out.println(-1);
                return;
            }
            // 승객 목적지로 이동
            flag = false;
            move(p);

            // 이동 불가능한 경우
            if(!flag) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(fuel);
    }
    static void move(int target) {
        boolean[][] visit = new boolean[n][n];
        Queue<Info> queue = new LinkedList<>();

        queue.add(new Info(taxi, 0));
        visit[taxi.x][taxi.y] = true;

        while (!queue.isEmpty()) {
            Info t = queue.poll();

            pair loc = t.loc;
            // 연료 부족
            if(fuel < t.cnt) return;

            // 목적지 도달
            if(des[target].x == loc.x && des[target].y == loc.y) {
                flag = true;

                // 연료, 택시 위치 갱신
                fuel += t.cnt;
                taxi = loc;
            }

            for(int i = 0 ; i < 4 ; i++) {
                int nx = loc.x + dx[i];
                int ny = loc.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(road[nx][ny] == 1 || visit[nx][ny]) continue;

                visit[nx][ny] = true;
                queue.add(new Info(new pair(nx, ny), t.cnt + 1));
            }
        }
    }
    static int find() {
        boolean[][] visit = new boolean[n][n];
        Queue<Info> queue = new LinkedList<>();

        queue.add(new Info(taxi, 0));
        visit[taxi.x][taxi.y] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int cnt = 0; // 걸린 거리

            // 같은 거리 일 때 : 행 번호 작은 순, 같은 행이면 열 번호 작은 순
            PriorityQueue<pair> pq = new PriorityQueue<>(new Comparator<pair>() {
                @Override
                public int compare(pair t1, pair t2) {
                    if(t1.x == t2.x) return t1.y - t2.y;
                    return t1.x - t2.x;
                }
            });

            while (size-- > 0) {
                Info t = queue.poll();

                pair loc = t.loc;
                // 연료 부족
                if(fuel < t.cnt) return -1;

                // 현재 위치에 승객 있는 경우
                if(road[loc.x][loc.y] > 1)  {
                    pq.add(loc);
                    cnt = t.cnt;
                }

                for(int i = 0 ; i < 4 ; i++) {
                    int nx = loc.x + dx[i];
                    int ny = loc.y + dy[i];

                    if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                    if(road[nx][ny] == 1 || visit[nx][ny]) continue;

                    visit[nx][ny] = true;
                    queue.add(new Info(new pair(nx, ny), t.cnt + 1));
                }
            }
            if(pq.isEmpty()) continue;

            // 다음 태울 승객
            pair p = pq.poll();
            flag = true;

            // 연료, 택시 위치 갱신
            fuel -= cnt;
            taxi = p;

            int target = road[p.x][p.y];
            road[p.x][p.y] = 0;

            return target;
        }
        return -1;
    }
    static class pair {
        int x, y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Info {
        pair loc;
        int cnt;

        Info(pair loc, int cnt) {
            this.loc = loc;
            this.cnt = cnt;
        }
    }
}
