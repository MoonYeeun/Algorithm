package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 12764 싸지방에 간 준하
// 우선순위 큐
public class Back_12764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[] people = new int[n]; // 어떤 사람이 어떤 자리 사용하는지 기록
        int[] record = new int[n]; // 각 자리를 사용한 사람 수 기록

        PriorityQueue<pair> room = new PriorityQueue<>(); // 싸지방에 들어온 사람 담을 큐
        PriorityQueue<Integer> computer = new PriorityQueue<>(); // 사용할 수 있는 컴퓨터 담을 큐

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int enterTime = Integer.parseInt(st.nextToken());
            int leaveTime = Integer.parseInt(st.nextToken());

            room.add(new pair(i, enterTime, true));
            room.add(new pair(i, leaveTime, false));
        }

        int idx = 0;
        while (!room.isEmpty()) {
            pair p = room.poll();

            if (!p.isEnter) {
                computer.add(people[p.people]);
                continue;
            }

            // 사용할 수 있는 자리 없는 경우 -> 자리 생성
            if (computer.isEmpty()) computer.add(idx++);

            int cur = computer.poll();
            record[cur]++;
            people[p.people] = cur;
        }

        System.out.println(idx);
        for (int i = 0; i < idx; i++) {
            System.out.print(record[i] + " ");
        }
    }

    public static class pair implements Comparable<pair> {
        int people, time;
        boolean isEnter;

        pair(int people, int time, boolean isEnter) {
            this.people = people;
            this.time = time;
            this.isEnter = isEnter;
        }

        @Override
        public int compareTo(pair pair) {
            return this.time - pair.time;
        }
    }
}
