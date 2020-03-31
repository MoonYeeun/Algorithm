package programmers;

import java.util.Arrays;

// 2019 카카오 인턴 모의고사 징검다리 건너기
// 이분탐색
public class Programmers_steppingstone {
    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;

        int[] arr = Arrays.copyOf(stones, stones.length);
        Arrays.sort(arr);
        int min = arr[0];
        int max = arr[arr.length - 1];
        int mid;
        int answer = 0;

        while (min <= max) {
            mid = (min + max) / 2;

            int cnt = 0;
            int cur = 0; // 0 이 연속된 디딤돌 수
            for(int i = 0 ; i < stones.length ; i++) {

                if(stones[i] >= mid) {
                    cnt = 0;
                    continue;
                }
                cnt++;
                cur = Math.max(cur, cnt);
            }
            // 더이상 돌다리 건널 수 없는 경우
            if(cur > k-1) {
                max = mid - 1;
            }
            else {
                answer = Math.max(answer, mid);
                min = mid + 1;
            }
        }
        System.out.println(answer);
    }
}
