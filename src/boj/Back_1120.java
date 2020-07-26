package boj;

import java.util.*;

// 백준 1120 문자열
// b문자열 앞에서부터 a문자열 비교해가면서 가장 많이 일치하는 수 찾기
// a 길이 - 일치하는 수 = 정답 (가장 많이 일치하는 지점에서 알파벳 붙이면 되므로)
public class Back_1120 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();

        int result = compare(A, B);
        System.out.println(A.length() - result);
    }
    static int compare(String a, String b) {
        int ans = 0;

        for(int i = 0 ; i <= b.length() - a.length() ; i++) {
            int cnt = 0;

            int idx = 0;
            for(int j = i ; j < i + a.length() ; j++) {
                if(b.charAt(j) == a.charAt(idx++)) cnt++;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
