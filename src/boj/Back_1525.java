package boj;

import java.util.*;

// 백준 1525 퍼즐
// 숫자판 문자로 두기
public class Back_1525 {
    static String num, target;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 9 ; i++) {
            sb.append(sc.nextInt());
        }
        num = sb.toString();
        target = "123456780";

        bfs();
    }
    static void bfs() {
        HashSet<String> set = new HashSet<>(); // 중복 체크
        Queue<pair> queue = new LinkedList<>();
        queue.add(new pair(num, 0));
        set.add(num);

        while (!queue.isEmpty()) {
            pair p = queue.poll();

            if(p.map.equals(target)) {
                System.out.println(p.cnt);
                return;
            }

            int idx = p.map.indexOf("0"); // 바꿀 수 있는 위치
            for(int i = 0 ; i < 4 ; i++) {
                int nx = idx / 3 + dx[i]; // 세로
                int ny = idx % 3 + dy[i]; // 가로

                if(nx < 0 || nx > 2 || ny < 0 || ny > 2) continue;

                String result = change(p.map, idx, nx * 3 + ny);

                if(set.contains(result)) continue;
                set.add(result);
                queue.add(new pair(result, p.cnt + 1));
            }
        }
        System.out.println(-1);
    }
    static String change(String num, int from, int to) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < 9 ; i++) {
            if(i == from) sb.append(num.charAt(to));
            else if(i == to) sb.append(num.charAt(from));
            else sb.append(num.charAt(i));
        }
        return sb.toString();
    }
    static class pair {
        String map;
        int cnt;

        pair(String map, int cnt) {
            this.map = map;
            this.cnt = cnt;
        }
    }
}
