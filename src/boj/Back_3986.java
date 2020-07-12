package boj;

import java.util.*;
// 백준 3986 좋은단어
// 스택
public class Back_3986 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int cnt = 0;
        while (n-- > 0) {
            String str = sc.next();

            if(check(str)) cnt++;
        }
        System.out.println(cnt);
    }
    static boolean check(String str) {
        Stack<Character> st = new Stack<>();

        for(int i = 0 ; i < str.length() ; i++) {
            char c = str.charAt(i);

            if(st.isEmpty() || st.peek() != c) st.push(c);
            else if(st.peek() == c) st.pop();
        }

        if(st.isEmpty()) return true;
        else return false;
    }
}
