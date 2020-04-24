package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 13913 숨바꼭질4
public class Back_13913 {
    static int[] path = new int[100001]; // 이전에 방문한 지점 저장할 배열
    static boolean[] visit = new boolean[100001]; // 방문 여부
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int n =  Integer.parseInt(st.nextToken());
        int k =  Integer.parseInt(st.nextToken());

        Queue<pair> queue = new LinkedList<>();
        queue.add(new pair(n, 0));
        path[n] = n;

        while (!queue.isEmpty()) {
            pair pair = queue.poll();
            int x = pair.loc;

            if(x == k) {
                System.out.println(pair.cnt);

                check(k, n);
                System.out.print(k);
                break;
            }

            int[] arr = {x-1, x+1, 2*x};
            for(int i : arr) {
                if(i < 0 || i > 100000 || visit[i]) continue;

                queue.add(new pair(i, pair.cnt+1));
                path[i] = x;
                visit[i] = true;
            }
        }
    }
    static void check(int idx, int n) {
        if(idx == n) return;
        else check(path[idx], n);

        System.out.print(path[idx] + " ");
    }
    static class pair {
        int loc, cnt;

        pair(int loc, int cnt) {
            this.loc = loc;
            this.cnt = cnt;
        }
    }
}
