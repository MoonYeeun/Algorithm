package boj;

import java.util.PriorityQueue;
import java.util.Scanner;

// 백준 13904 과제
// 유니온파인드
// 점수가 높은 순서대로 정렬 -> 해당 날짜에 과제
// 못할 경우 과제할 날짜 줄여나감
public class Back_13904 {
    static PriorityQueue<pair> pq = new PriorityQueue<>();
    static int[] root = new int[10001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i = 0 ; i < 10001 ; i++) {
            root[i] = i;
        }
        for(int i = 0 ; i < n ; i++) {
            pq.add(new pair(sc.nextInt(), sc.nextInt()));
        }
        int answer = 0;
        while (!pq.isEmpty()) {
            pair pair = pq.poll();
            int possible = find(pair.day);

            if(possible != 0) {
                union(possible, possible -1);
                answer += pair.score;
            }
        }
        System.out.println(answer);
    }
    static int find(int x) {
        if(root[x] == x)
            return x;
        return root[x] = find(root[x]);
    }
    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        root[x] = y;
    }
    static class pair implements Comparable<pair>{
        int day, score;

        pair(int day, int score) {
            this.day = day;
            this.score = score;
        }

        @Override
        public int compareTo(pair pair) {
            return pair.score - this.score;
        }
    }
}
