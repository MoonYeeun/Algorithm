package programmers;

import java.util.*;

// 프로그래머스 위클리챌린지 6 복서 정렬하기
public class Programmers_weekly6 {
    public static void main(String[] args) {
        int[] weights = {50, 82, 75, 120};
        //int[] weights = {60, 70, 60};
        String[] head2head = {"NLWL", "WNLL", "LWNW", "WWLN"};
        //String[] head2head = {"NNN","NNN","NNN"};
        int[] result = solution(weights, head2head);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[] solution(int[] weights, String[] head2head) {
        int size = weights.length;
        int[] answer = new int[size];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for (int i = 0; i < size; i++) {
            char[] result = head2head[i].toCharArray();
            int total = 0;
            int justWin = 0;
            int heavyWin = 0;

            for (int j = 0; j < size; j++) {
                if (i == j || result[j] == 'N') continue;

                total++;
                if (result[j] == 'W') {
                    justWin++;
                    if (weights[i] < weights[j]) heavyWin++;
                }
            }
            double rate = total == 0 ? 0 : (justWin * 100.0 / total);
            pq.add(new Pair(rate, heavyWin, weights[i], i + 1));
        }

        int idx = 0;
        while (!pq.isEmpty()) {
            answer[idx++] = pq.poll().num;
        }
        return answer;
    }

    public static class Pair implements Comparable<Pair> {
        double win;
        int moreWeightWin, weight, num;

        Pair(double win, int moreWeightWin, int weight, int num) {
            this.win = win;
            this.moreWeightWin = moreWeightWin;
            this.weight = weight;
            this.num = num;
        }

        public int compareTo(Pair pair) {
            if (this.win == pair.win) {
                if (this.moreWeightWin == pair.moreWeightWin) {
                    if (this.weight == pair.weight) {
                        return this.num - pair.num;
                    }
                    return pair.weight - this.weight;
                }
                return pair.moreWeightWin - this.moreWeightWin;
            }
            return pair.win > this.win ? 1 : -1;
        }
    }
}
