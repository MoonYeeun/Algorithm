package boj;

import java.util.*;
// 백준 1389 케빈베이컨의 6단계 법칙 (Bfs)
public class Back_1389 {
    static int[][] map;
    static boolean[] visit;
    static Queue<pair> queue = new LinkedList<>();
    static int n, m;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n+1][n+1];
        ArrayList<kevinBacon> list = new ArrayList<>();

        for(int i = 0 ; i < m ; i++) {
            int num1 = sc.nextInt();
            int num2 = sc.nextInt();
            map[num1][num2] = map[num2][num1] = 1;
        }
        for(int i = 1 ; i <= n ; i++) {
            visit = new boolean[n+1];
            visit[i] = true;
            for(int j = 1 ; j <= n ; j++) {
                if(i != j && !visit[j] && (map[i][j] == 1 || map[j][i] == 1)) {
                    visit[j] = true;
                    queue.add(new pair(j,1));
                }
            }
            list.add(new kevinBacon(bfs(), i));
        }
        Collections.sort(list);
        System.out.println(list.get(0).idx);
    }
    static int bfs() {
        int answer = 0;
        while(!queue.isEmpty()) {
            pair pair = queue.poll();
            answer += pair.cnt;
            for(int i = 1; i <= n ; i++) {
                if(!visit[i] && (map[pair.x][i] == 1 || map[i][pair.x] == 1)) {
                    visit[i] = true;
                    queue.add(new pair(i, pair.cnt+1));
                }
            }
        }
        return answer;
    }
    static class pair{
        int x;
        int cnt;

        pair(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }
    }
    static class kevinBacon implements Comparable<kevinBacon> {
        int num;
        int idx;

        kevinBacon(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }

        @Override
        public int compareTo(kevinBacon kevinBacon) {
            if (this.num == kevinBacon.num)
                return this.idx > kevinBacon.idx ? 1 : -1;
            return this.num - kevinBacon.num;
        }
    }
}
