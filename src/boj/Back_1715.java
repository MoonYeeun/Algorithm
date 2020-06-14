package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 1715 카드 정렬하기
// 힙
public class Back_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(bf.readLine());
            arr[i] = Integer.parseInt(st.nextToken());

            pq.add(arr[i]);
        }

        int ans = 0;
        int card1, card2;
        while (!pq.isEmpty()) {
            card1 = pq.poll();

            if(!pq.isEmpty()) {
                card2 = pq.poll();
                ans += card1 + card2;

                pq.add(card1 + card2); // 크기 가장 적은 2개 카드 더하기
            }
        }
        System.out.println(ans);
    }
}
