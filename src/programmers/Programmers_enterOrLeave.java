package programmers;

import java.util.*;

// 프로그래머스 위클리챌린지 7 입실 퇴실
public class Programmers_enterOrLeave {
    public static void main(String[] args) {
        int[] enter = {1, 3, 2};
        int[] leave = {1, 2, 3};
        int[] result = solution(enter, leave);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[] solution(int[] enter, int[] leave) {
        int size = enter.length;
        int[] answer = new int[size];
        int[] order = new int[size + 1]; // 들어온 순서 idx

        int enter_idx = 0;
        int leave_idx = 0;
        Arrays.fill(order, -1);

        while (leave_idx < size) {
            if (enter_idx != size) {
                order[enter[enter_idx]] = enter_idx;
                // 해당 사람이 들어오기 전 방에 있었던 사람
                answer[enter[enter_idx] - 1] += enter_idx - leave_idx;
            }

            while (leave_idx < size && order[leave[leave_idx]] != -1) {
                // 해당 사람이 들어온 후 들어온 사람
                answer[leave[leave_idx] - 1] += enter_idx - order[leave[leave_idx]];
                leave_idx++;
            }
            enter_idx++;
        }
        return answer;
    }
}
