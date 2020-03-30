package programmers;

import java.util.Arrays;

// 프로그래머스 입국심사 (이분탐색)
public class Programmers_immigration {
    public static void main(String[] args) {
        int n = 6;
        //int n = 1;
        int[] times = {7, 10};
        //int[] times = {2, 2};

        Arrays.sort(times);

        long left = 0;
        long right = (long)times[times.length-1] * (long)n; // 최악의 시간
        long answer = Long.MAX_VALUE;

        while (left <= right) {
            long people = 0; // 해당 시간까지 처리할 수 있는 사람 수
            long mid = (left + right) / 2;

            for(int i : times) {
                people += mid / i;
            }
            // 해당 시간에 심사를 다 완료하지 못한 경우
            if(people < n) {
                left = mid + 1;
            }
            // 시간이 여유로운 경우
            if(people >= n) {
                if(mid < answer) answer = mid;
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }
}
