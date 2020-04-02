package boj;

import java.util.*;

// 백준 3190 뱀
public class Back_3190 {
    static HashSet<pair> apple = new HashSet<>(); // 사과위치
    static HashSet<pair> snake = new HashSet<>(); // 뱀 위치
    static HashMap<Integer, String> dir_info = new HashMap<>(); // 방향정보
    static Queue<pair> queue = new LinkedList<>();
    static int n, answer;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int k = sc.nextInt();
        for(int i = 0 ; i < k ; i++) {
            apple.add(new pair(sc.nextInt(), sc.nextInt()));
        }
        int l = sc.nextInt();
        for(int i = 0 ; i < l ; i++) {
            int time = sc.nextInt();
            String dir = sc.next();
            dir_info.put(time, dir);
        }
        queue.add(new pair(1, 1));
        move();
        System.out.println(answer);
    }
    static void move() {
        // 현재 뱀의 위치
        int cur_x = 1;
        int cur_y = 1;
        int dir = 0; // 방향

        while (true) {
            answer++;
            cur_x += dx[dir];
            cur_y += dy[dir];

            if(cur_x > n || cur_x < 1 || cur_y > n || cur_y < 1
                    || snake.contains(new pair(cur_x, cur_y))) break;

            queue.add(new pair(cur_x, cur_y));
            snake.add(new pair(cur_x, cur_y));
            // 사과 없는 경우
            if(!apple.contains(new pair(cur_x, cur_y)))
                snake.remove(queue.poll());
            else
                apple.remove(new pair(cur_x, cur_y));

            // 방향 바꿔주기
            if(dir_info.containsKey(answer)) {
                if(dir_info.get(answer).equals("L"))
                    dir = (dir + 4 - 1) % 4;
                else
                    dir = (dir + 4 + 1) % 4;
            }
        }
    }
    static class pair {
        int x, y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public boolean equals(Object o) {
            if (o instanceof pair) {
                pair p = (pair)o;
                return p.x == x && p.y == y;
            }
            return false;
        }
        public int hashCode() {
            return new Integer(x).hashCode() * 31 + new Integer(y).hashCode();
        }
    }
}
