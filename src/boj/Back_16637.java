package boj;

import java.util.*;

// 백준 16637 괄호추가하기
public class Back_16637 {
    static int n, ans;
    static String str;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        str = sc.next();

        ans = Integer.MIN_VALUE;
        dfs(0, 0);
        System.out.println(ans);
    }
    static void dfs(int idx, int cur) {
        if(idx >= n) {
            ans = Math.max(ans, cur);
            return;
        }

        char op = idx == 0 ? '+' : str.charAt(idx-1);
        // 괄호 추가 (괄호 추가할 부분 계산 후 -> 그 값과 cur 연산)
        if(idx + 2 < n) {
            int result = cal(str.charAt(idx) - '0', str.charAt(idx+2) -'0', str.charAt(idx+1));
            dfs(idx + 4, cal(cur, result, op));
        }
        // 추가 안함 (cur 와 현재 idx 값 연산)
        dfs(idx + 2, cal(cur, str.charAt(idx) - '0', op));
    }
    static int cal(int a, int b, char op) {
        if(op == '+') return a + b;
        else if(op == '-') return a - b;
        else return a * b;
    }
}
