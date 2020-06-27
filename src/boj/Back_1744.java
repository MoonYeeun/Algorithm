package boj;

import java.util.*;

// 백준 1744 수 묶기
// 음수 양수 따로 계산
public class Back_1744 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        PriorityQueue<Integer> pos_pq = new PriorityQueue<>(Collections.reverseOrder()); //양수
        PriorityQueue<Integer> neg_pq = new PriorityQueue<>(); // 음수
        for(int i = 0 ; i < n ; i++) {
            int num = sc.nextInt();

            if(num > 0) pos_pq.add(num);
            else neg_pq.add(num);
        }

        int ans = 0;
        int num1, num2;
        // 양수 계산
        while (!pos_pq.isEmpty()) {
            num1 = pos_pq.poll();

            if(!pos_pq.isEmpty()) {
                num2 = pos_pq.poll();

                int mul = num1 * num2;
                int sum = num1 + num2;

                ans += mul > sum ? mul : sum;
                continue;
            }
            ans += num1;
        }
        // 음수 계산
        while (!neg_pq.isEmpty()) {
            num1 = neg_pq.poll();

            if(!neg_pq.isEmpty()) {
                num2 = neg_pq.poll();

                ans += (num1 * num2);
                continue;
            }
            ans += num1;
        }
        System.out.println(ans);
    }
}
