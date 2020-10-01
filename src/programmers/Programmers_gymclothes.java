package programmers;

import java.util.*;

// 프로그래머스 체육복
public class Programmers_gymclothes {
    public static void main(String[] args) {
        int n = 5;
        int[] lost = {2, 4};
        int[] reserve = {1, 3, 5};

        solution(n, lost, reserve);
    }

    static public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        PriorityQueue<pair> pq = new PriorityQueue<>();
        HashSet<Integer> set = new HashSet<>(); // 체육복 빌릴 수 있는 학생

        for (int i : reserve) {
            set.add(i);
        }

        for (int i : lost) {
            // 여벌 체육복 있는데 도난 당한 경우
            if (set.contains(i)) {
                answer++;
                set.remove(i);
                continue;
            }

            int cnt = 0;
            if (i >= 1 && set.contains(i - 1)) cnt++;
            if (i <= n && set.contains(i + 1)) cnt++;

            if (cnt > 0) {
                pq.add(new pair(i, cnt));
            }
        }

        while (!pq.isEmpty()) {
            pair p = pq.poll();

            if (p.student + 1 <= n && set.contains(p.student + 1)) {
                answer++;
                set.remove(p.student + 1);
                continue;
            }

            if (p.student - 1 > 0 && set.contains(p.student - 1)) {
                answer++;
                set.remove(p.student - 1);
            }
        }
        return answer;
    }

    static class pair implements Comparable<pair> {
        int student, possible;

        pair(int student, int possible) {
            this.student = student;
            this.possible = possible;
        }

        @Override
        public int compareTo(pair pair) {
            return this.possible - pair.possible;
        }
    }
}
