package boj;

import java.util.*;

// 백준 2023 신기한 소수
// 백트래킹
public class Back_2023 {
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dfs(0, "");
    }
    static void dfs(int cnt, String s) {
        if(cnt == n) {
            System.out.println(s);
            return;
        }

        for(int i = 1 ; i <= 9 ; i++) {
            if(isPrime(Integer.parseInt(s + i)))
                dfs(cnt + 1, s + i);
        }
    }
    public static boolean isPrime(int k) {
        if (k == 1) {
            return false;
        }

        int sqrt = (int) Math.sqrt(k);
        for (int i = 2; i <= sqrt; i++) {
            if (k % i == 0) return false;
        }
        return true;
    }
}
