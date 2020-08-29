package boj;

import java.util.*;

// 백준 2294 동전2
public class Back_2294 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] dp = new int[100001];

        HashSet<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int coin = sc.nextInt();
            set.add(coin);
            queue.add(coin);
            dp[coin] = 1;
        }

        int answer = -1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == k) {
                answer = dp[cur];
                break;
            }

            Iterator<Integer> it = set.iterator();
            while (it.hasNext()) {
                int newCoin = cur + it.next();

                if (newCoin > k || dp[newCoin] != 0) continue;

                dp[newCoin] = dp[cur] + 1;
                queue.add(newCoin);
            }
        }
        System.out.println(answer);
    }
}
