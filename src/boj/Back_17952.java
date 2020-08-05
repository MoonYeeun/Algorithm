package boj;

import java.io.*;
import java.util.*;

// 백준 17952 과제는 끝나지 않아!
// 우선순위큐
public class Back_17952 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<pair> pq = new PriorityQueue<>();

        int ans = 0;
        for(int i = 1; i <= n ; i++) {
            st = new StringTokenizer(br.readLine());

            int isTask = Integer.parseInt(st.nextToken());
            int score = 0; int time = 0; int start = 0;

            if(isTask == 0) {
                if(pq.isEmpty()) continue;

                pair p = pq.poll();
                score = p.score;
                time = p.remain;
                start = p.start;
            } else {
                score = Integer.parseInt(st.nextToken());
                time = Integer.parseInt(st.nextToken());
                start = i;
            }

            if(time - 1 == 0) ans += score;
            else pq.add(new pair(score, time -1, start));
        }
        System.out.println(ans);
    }
    static class pair implements Comparable<pair>{
        int score, remain, start;

        pair(int score, int remain, int start) {
            this.score = score;
            this.remain = remain;
            this.start = start;
        }
        @Override
        public int compareTo(pair pair) {
            return pair.start - this.start;
        }
    }
}
