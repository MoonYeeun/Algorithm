package LeetCode;

import java.util.Deque;
import java.util.LinkedList;

// 862. Shortest Subarray with Sum at Least K
// prefixSum + dequeue
// 현재 값 보다 큰 값이 이전 prefix에 있을 경우 빼주기 → 그래야 뒤쪽 값을 다 확인해 볼 수 있음
public class LC862_Shortest_Subarray_with_Sum_atLeast_K {
    public static void main(String[] args) {
        int[] arr = {77, 19, 35, 10, -14};
        int k = 19;

        int result = shortestSubarray(arr, k);
        System.out.println(result);
    }

    public static int shortestSubarray(int[] A, int K) {
        int ans = Integer.MAX_VALUE;
        int[] prefix = new int[A.length + 1];
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 1; i <= A.length; i++) {
            prefix[i] = prefix[i - 1] + A[i - 1];
        }

        for (int i = 0; i <= A.length; i++) {
            while (!dq.isEmpty() && prefix[i] - prefix[dq.getFirst()] >= K) {
                ans = Math.min(ans, i - dq.pollFirst());
            }
            while (!dq.isEmpty() && prefix[i] < prefix[dq.getLast()]) dq.pollLast();

            dq.offer(i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
