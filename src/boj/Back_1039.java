package boj;

import java.util.*;

// 백준 1039 교환
// BFS로 각 횟수별 교환할 수 있는 경우 다 찾기
public class Back_1039 {
    static int n, k;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        System.out.println(bfs());
    }
    static int bfs() {
        Queue<pair> queue = new LinkedList<>();
        queue.add(new pair(String.valueOf(n), 0));
        boolean[][] visit = new boolean[k+1][1000001];
        visit[0][n] = true;

        while (!queue.isEmpty()) {
            if(queue.peek().cnt == k) break;

            pair p = queue.poll();
            int len = p.num.length();

            for(int i = 0 ; i < len - 1; i++) {
                for(int j = i + 1; j < len ; j++) {
                    if(i == 0 && p.num.charAt(j) == '0') continue;

                    String result = swap(i, j, p.num);
                    if(visit[p.cnt + 1][Integer.parseInt(result)]) continue;

                    visit[p.cnt + 1][Integer.parseInt(result)] = true;
                    queue.add(new pair(result, p.cnt + 1));
                }
            }
        }
        int ans = -1;
        while (!queue.isEmpty()) {
            ans = Math.max(ans, Integer.parseInt(queue.poll().num));
        }
        return ans;
    }
    static String swap(int a, int b, String num) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < num.length() ; i++) {
            if(i == a) sb.append(num.charAt(b));
            else if(i == b) sb.append(num.charAt(a));
            else sb.append(num.charAt(i));
        }

        return sb.toString();
    }
    static class pair{
        String num;
        int cnt;

        pair(String num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
