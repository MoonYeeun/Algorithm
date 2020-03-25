package boj;

import java.util.*;

// 백준 16947 서울 지하철 2호선
/*
1. dfs 로 순환선 체크
2. bfs 로 거리 계산
* */
public class Back_16947 {
    static ArrayList<Integer> map[]; // 역 정보
    static boolean[] cycle; // 순환선인지 판단
    static boolean[] visit; // 방문 여부
    static int[] answer; // 정답 정보
    static Queue<Integer> queue = new LinkedList<>();
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new ArrayList[n];
        cycle = new boolean[n];

        for(int i = 0 ; i < n ; i++) {
            map[i] = new ArrayList<>();
        }
        // 인접 리스트 입력
        for(int i = 0 ; i < n ; i++) {
            int station1 = sc.nextInt()-1;
            int station2 = sc.nextInt()-1;

            map[station1].add(station2);
            map[station2].add(station1);
        }
        // 순환선 체크
        for(int i = 0 ; i < n ; i++) {
            visit = new boolean[n];
            findCycle(i, i, 0);
        }
        // 거리 계산
        answer = new int[n];
        Arrays.fill(answer, -1);
        bfs();

        for(int i : answer) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    static void findCycle(int start, int cur, int cnt) {
        if(start == cur && cnt >= 2) {
            cycle[start] = true;
            queue.add(start);
            return;
        }
        visit[cur] = true;

        for(int station : map[cur]) {
            if(!visit[station])
                findCycle(start, station, cnt + 1);

            else if(station == start && cnt >= 2)
                findCycle(start, station, cnt + 1);
        }
    }
    static void bfs() {
        while (!queue.isEmpty()) {
            int station = queue.poll();

            if(cycle[station])
                answer[station] = 0;

            for(int i : map[station]) {
                if(answer[i] != -1) continue;
                queue.add(i);
                answer[i] = answer[station] + 1;
            }
        }
    }
}
