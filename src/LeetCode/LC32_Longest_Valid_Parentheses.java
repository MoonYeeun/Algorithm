package LeetCode;

import java.util.*;

// 스택
// 1. 올바른 괄호 체크
// 2. 올바른 괄호 중 가장 긴 것 계산
public class LC32_Longest_Valid_Parentheses {
    public static void main(String[] args) {
        //String s = "(()";
        //String s = ")()())";
        String s = ")(";
        int result = longestValidParentheses(s);
        System.out.println(result);
    }

    static public int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        boolean[] checked = new boolean[s.length()];
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') st.push(i); // 해당 괄호 인덱스 저장
            else {
                if (st.isEmpty()) continue;
                checked[i] = checked[st.pop()] = true; // 올바른 괄호의 경우 true로 체크
            }
        }

        int start = -1;
        int end = 0;
        while (end < s.length()) {
            // 올바른 괄호 시작되는 지점
            if (checked[end] && start != -1) {
                start = end;
            } else if (!checked[end]) {
                if (start != -1) ans = Math.max(ans, end - start);
                start = -1;
            }

            if (checked[end] && end == s.length() - 1) ans = Math.max(ans, end - start + 1);
            end++;
        }
        return ans;
    }
}
