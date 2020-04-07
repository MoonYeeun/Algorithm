package programmers;

import java.util.*;

// 프로그래머스 프린터
public class Programmers_printer {
    public static void main(String[] args) {
        //int[] priorities = {2, 1, 3, 2};
        int[] priorities = {1, 1, 9, 1, 1, 1};
        //int location = 2;
        int location = 0;

        Queue<pair> queue = new LinkedList<>();
        int len = priorities.length;
        for(int i = 0 ; i < len; i++) {
            queue.add(new pair(i, priorities[i]));
        }

        Arrays.sort(priorities);

        int idx = len -1;
        int answer = 0;
        while (true) {
            pair pair = queue.poll();
            if(pair.priority == priorities[idx] && pair.idx == location) {
                answer++;
                break;
            }
            if(pair.priority == priorities[idx]) {
                idx--;
                answer++;
                continue;
            }
            queue.add(new pair(pair.idx, pair.priority));
        }
        System.out.println(answer);
    }
    static class pair {
        int idx, priority;

        pair(int idx, int priority) {
            this.idx = idx;
            this.priority = priority;
        }
    }
}
