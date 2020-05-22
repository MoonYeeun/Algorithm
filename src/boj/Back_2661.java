package boj;

import java.util.*;

// 백준 2661 좋은 수열
// 백트래킹
// 매번 숫자 붙일 때마다 (1 ~ 숫자 길이 /2) 만큼 좋은 수열인지 체크 !
public class Back_2661 {
    static int n, ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        ans = Integer.MAX_VALUE;

        dfs(0, "");
    }
    static boolean dfs(int cnt, String num) {
        if(cnt == n) {
            System.out.println(num);
            return true;
        }

        for(int i = 1 ; i <= 3; i++) {
            String temp = num + i;
            if(check(temp)) {
                if(dfs(cnt + 1, temp)) return true;
            }
        }
        return false;
    }
    static boolean check(String num) {
        int len = num.length();

        for(int i = 1 ; i <= len / 2 ; i++) {
            String a = num.substring(len - i);
            String b = num.substring(len - (2*i), len - i);

            if(a.equals(b)) return false;
        }
        return true;
    }
}
