package programmers;

import java.util.*;

// 프로그래머스 2017 팁스다운 - 짝지어 제거하기
// 스택
public class Programmers_removePair {
    public static void main(String[] args) {
        String s = "baabaa";
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        Stack<Character> st = new Stack<>();

        for (char c : s.toCharArray()) {
            if (st.isEmpty() || st.peek() != c) st.add(c);
            else st.pop();
        }
        return st.isEmpty() ? 1 : 0;
    }
}
