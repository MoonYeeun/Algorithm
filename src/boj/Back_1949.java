package boj;

import java.util.*;

// ⭐️ 백준 1949 우수마을
// 트리 + dp
// 현재 마을이 우수마을 : 다음 마을 우수마을 x
// 현재 마을이 우수마을 x : 다음 마을 우수마을 or 우수마을 x
public class Back_1949 {
    static int[] people;
    static int[][] dp;
    static ArrayList<Integer> village[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        village = new ArrayList[n+1];
        people = new int[n+1];
        dp = new int[n+1][2];

        for(int i = 1 ; i <= n ; i++) {
            people[i] = sc.nextInt();

            village[i] = new ArrayList<>();
            Arrays.fill(dp[i], -1);
        }

        for(int i = 0 ; i < n-1 ; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();

            village[v1].add(v2);
            village[v2].add(v1);
        }

        // 우수마을 지정 -> 1 : 우수마을o 0 : 우수마을x
        int ans = Math.max(goodVillage(1, 1, 1), goodVillage(1, 0, 1));
        System.out.println(ans);
    }
    static int goodVillage(int s, int isGood, int prev) {
        if(dp[s][isGood] != -1) return dp[s][isGood]; // 이미 방문한적 있을 경우

        dp[s][isGood] = 0;

        for(int i : village[s]) {
            if(prev == i) continue; // 이전에 방문한 노드는 방문 x

            if(isGood == 0) dp[s][isGood] += Math.max(goodVillage(i, 1, s), goodVillage(i, 0, s));
            else dp[s][isGood] += goodVillage(i, 0, s);
        }

        if(isGood == 1) dp[s][isGood] += people[s];
        return dp[s][isGood];
    }
}
