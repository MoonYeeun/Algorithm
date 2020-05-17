package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 9019 DSLR
public class Back_9019 {
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < t ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            visit = new boolean[10000];

            int ori = Integer.parseInt(st.nextToken());
            int fin = Integer.parseInt(st.nextToken());

            System.out.println(bfs(ori, fin));
        }
    }
    static String bfs(int ori, int fin) {
        String result = "";
        Queue<pair> queue = new LinkedList<>();
        queue.add(new pair(ori, ""));

        while (!queue.isEmpty()) {
            pair p = queue.poll();

            if(p.num == fin) {
                result = p.command;
                break;
            }
            // 'D'
            int D = (p.num * 2) % 10000;
            if(!visit[D]) {
                visit[D] = true;
                queue.add(new pair(D, p.command + 'D'));
            }
            // 'S'
            int S = p.num -1 < 0 ? 9999 : p.num -1;
            if(!visit[S]) {
                visit[S] = true;
                queue.add(new pair(S, p.command + 'S'));
            }
            // 'L'
            int L = (p.num % 1000) * 10 + (p.num / 1000);
            if(!visit[L]) {
                visit[L] = true;
                queue.add(new pair(L, p.command + 'L'));
            }
            // 'R'
            int R = (p.num % 10) * 1000 + (p.num / 10);
            if(!visit[R]) {
                visit[R] = true;
                queue.add(new pair(R, p.command + 'R'));
            }
        }
        return result;
    }
    static class pair {
        int num;
        String command;

        pair(int num, String command) {
            this.num = num;
            this.command = command;
        }
    }
}
