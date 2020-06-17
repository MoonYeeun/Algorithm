package boj;

import java.util.*;

// 백준 2568 전기줄2
// lower bound 로 구하는 lis 요소는 정확하지 않다 (개수는 맞음)!
// 정확한 요소 구하기 위해서는 record 배열에 i 별 idx 값 저장 후 내림차순으로 체크해주어야 함 !
// 참고 : https://yhwan.tistory.com/21
public class Back_2568 {
    static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[500001]; // a 전봇대와 연결된 전봇대 b
        int[] record = new int[500001]; // 각 i가 dp 어느 idx에 들어가는 지 기록
        dp = new int[500001]; // 최장 수열의 길이 구하기

        for(int i = 1 ; i <= n ; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            arr[s] = e;
        }

        int idx = 1;
        dp[1] = arr[1];
        for(int i = 1; i <= 500000 ; i++) {
            if(arr[i] == 0) continue;

            if(dp[idx] < arr[i]) {
                dp[++idx] = arr[i];
                record[i] = idx;
            }
            else {
                int check = lower_bound(idx, arr[i]);
                record[i] = check;
                dp[check] = arr[i];
            }
        }
        // LIS의 요소 구하기
        int s = idx;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 500000; i > 0 ; i--) {
            if(record[i] == 0) continue;

            if(idx <= 0) break;
            if(record[i] == s) {
                pq.add(i);
                s--;
            }
        }

        System.out.println(n - pq.size()); // 제거 개수
        for(int i = 1; i <= 500000 ; i++) {
            if(arr[i] == 0) continue;

            if(!pq.isEmpty() && pq.peek() <= i) {
                pq.poll();
                continue;
            }
            System.out.println(i);
        }
    }
    static int lower_bound(int end, int n) {
        int start = 0;

        while (start < end) {
            int mid = (start + end) / 2;

            if(dp[mid] >= n) end = mid;
            else start = mid + 1;
        }
        return end;
    }
}
