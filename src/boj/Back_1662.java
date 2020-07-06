package boj;

import java.util.*;

// 백준 1662 압축
// 재귀
public class Back_1662 {
    static int start;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        System.out.println(decompress(0, s));
    }
    static int decompress(int idx, String s) {
        int ans = 0;

        for(int i = idx ; i < s.length() ; i++) {
            char c = s.charAt(i);

            if(c == '(') {
                int num = s.charAt(i-1) - '0';
                int result = decompress(i+1, s);

                ans += num * result - 1;

                i = start; // 시작위치 갱신
                continue;
            }
            if(c == ')') {
                start = i++;
                break;
            }

            ans++;
        }

        return ans;
    }
}
