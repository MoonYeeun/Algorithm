package boj;

import java.io.*;
import java.util.*;

// 백준 17612 쇼핑몰
// 우선순위큐..2개
public class Back_17612 {
    static int n, k;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        PriorityQueue<Line> wait_pq = new PriorityQueue<>(new Comparator<Line>() {
            @Override
            public int compare(Line l1, Line l2) {
                if(l1.time == l2.time) {
                    return l1.counter - l2.counter;
                }
                return l1.time - l2.time;
            }
        });
        PriorityQueue<Line> leave_pq = new PriorityQueue<>(new Comparator<Line>() {
            @Override
            public int compare(Line l1, Line l2) {
                if(l1.time == l2.time) {
                    return l2.counter - l1.counter;
                }
                return l1.time - l2.time;
            }
        });

        long ans = 0;
        long cnt = 1;
        for(int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine());
            int people = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if(wait_pq.size() < k) {
                wait_pq.add(new Line(people, wait_pq.size() + 1, w));
                continue;
            }

            Line end = wait_pq.poll(); // 계산 다 끝난 사람
            leave_pq.add(end);
            wait_pq.add(new Line(people, end.counter, end.time + w));
        }
        while (!wait_pq.isEmpty()) {
            leave_pq.add(wait_pq.poll()); // 계산 다 끝난 사람
        }

        while (!leave_pq.isEmpty()) {
            ans += cnt++ * leave_pq.poll().people;
        }
        System.out.println(ans);
    }
    static class Line {
        int people, counter, time;

        Line(int people, int counter, int time) {
            this.people = people;
            this.counter = counter;
            this.time = time;
        }
    }
}
