package boj;
import java.util.*;

// 백준 15686 치킨배달
/*
1. 치킨집 좌표, 집 좌표 저장
2. dfs로 m 개의 치킨 집 선택
3. 모든 경우 탐색 후 최소 거리 합 중 가장 작은 것 선택
*/

public class Back_15686 {
    static int[][] map;
    static boolean[] visit;
    static int n, m;
    static int answer = Integer.MAX_VALUE;
    static ArrayList<pair> chicken = new ArrayList<>();
    static ArrayList<pair> home = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n+1][n+1];
        visit = new boolean[13];

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                map[i][j] = sc.nextInt();

                if(map[i][j] == 2) chicken.add(new pair(i, j));
                if(map[i][j] == 1) home.add(new pair(i, j));
            }
        }
        dfs(0,0);
        System.out.println(answer);
    }
    static void dfs(int idx, int selected) {
        if(selected == m) {
            int temp = 0;
            for(int i = 0 ; i < home.size() ; i++) {
                int distance = Integer.MAX_VALUE;
                for(int j = 0 ; j < chicken.size() ; j++) {
                    if(visit[j])
                        distance = Math.min(distance, distance(home.get(i), chicken.get(j)));
                }
                temp += distance;
            }
            answer = Math.min(answer, temp);
            return;
        }

        if(idx == chicken.size()) return;
        // 치킨 집 선택 o
        visit[idx] = true;
        dfs(idx+1, selected+1);
        // 치킨 집 선택 x
        visit[idx] = false;
        dfs(idx+1, selected);
    }
    static int distance(pair a , pair b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
    static class pair {
        int x;
        int y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
