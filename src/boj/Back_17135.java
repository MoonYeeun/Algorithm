package boj;

import java.util.*;

// 백준 17135 캐슬디펜스
// 1. 궁수 위치 정하기
// 2. 공격하기 (같은 거리일 경우 왼쪽부터)
// 3. 제거할 수 있는 최대수 구하기
public class Back_17135 {
    static int n, m, d, ans;
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        d = sc.nextInt();
        map = new int[n][m];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        choose(0, 0, 0);
        System.out.println(ans);
    }
    // 공격
    static int attack(int bit) {
        int cnt = 0;
        boolean[][] killed = new boolean[n][m];
        ArrayList<Integer> list = new ArrayList<>();
        Queue<pair> queue = new LinkedList<>();

        // 성 설치 된 경우
        for(int i = 0 ; i < m ; i++) {
            if((bit & (1<<i)) == (1<<i)) list.add(i);
        }
        for(int i = n ; i > 0 ; i--) {
            for(int ach : list) {
                boolean flag = false;
                // 각 거리 별로 쏠 수 있는 위치
                for(int dis = 1 ; dis <= d ; dis++) {

                    int x = i-1;
                    int y = ach - dis + 1;

                    for(int turn = 1 ; turn < 2 * dis ; turn++) {
                        if(x >= 0 && x < n && y >= 0 && y < m) {
                            if(map[x][y] == 1 && !killed[x][y]) {
                                queue.add(new pair(x, y));
                                flag = true;
                                break;
                            }
                        }
                        // 왼 -> 오 이동
                        y++;
                        if(y <= ach) x--;
                        else x++;
                    }
                    if(flag) break;
                }
            }
            // 적 사라지도록
            while (!queue.isEmpty()) {
                pair p = queue.poll();
                if(!killed[p.x][p.y]) {
                    killed[p.x][p.y] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
    // 궁수 위치 정하기
    static void choose(int cnt, int idx, int bit) {
        if(cnt == 3) ans = Math.max(ans, attack(bit));
        if(idx >= m) return;

        // 해당 idx 고르기
        choose(cnt + 1, idx + 1, bit | (1<<idx));
        // 안고르기
        choose(cnt, idx + 1, bit);
    }
    static class pair {
        int x, y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}