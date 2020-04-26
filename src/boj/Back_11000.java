package boj;

import java.util.*;

// 백준 11000 강의실 배정
// 우선순위큐
public class Back_11000 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] arr = new int[n][2];
        for(int i = 0 ; i < n ; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();

            arr[i][0] = s;
            arr[i][1] = t;
        }
        // 강의 시작 시간 일찍인 순서로 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0 ; i < n ; i++) {
            if(!pq.isEmpty() && pq.peek() <= arr[i][1]) pq.poll();
            pq.add(arr[i][1]);
        }

        System.out.println(pq.size());
    }
}
