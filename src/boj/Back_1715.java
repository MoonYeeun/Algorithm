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

        if(pq.size() == 1) System.out.println(0);
        else {
            int ans = 0;
            while (!pq.isEmpty()) {
                int a = 0;
                boolean flag = false;

                for(int i = 0 ; i < 2 ; i++) {
                    if(pq.isEmpty()) {
                        flag = true;
                        break;
                    }
                    a += pq.poll(); // 가장 적은 카드 묶음 2개 더하기
                }
                ans += a;
                if(!flag && !pq.isEmpty()) pq.add(a);
            }
            System.out.println(ans);
        }
    }
}
