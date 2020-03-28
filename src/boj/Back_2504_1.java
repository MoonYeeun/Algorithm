package boj;

import java.util.Scanner;
import java.util.Stack;

// 백준 2504 괄호의 값
/*
올바른 괄호의 경우 ), ] 만났을 때 한번에 계산해버리기
* */
public class Back_2504_1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Stack<Character> st = new Stack<>();
        String str = scan.next();

        int mul = 1, ans = 0;
        for(int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);

            if(ch == '(') {
                st.push(ch);
                mul *= 2;
            }

            else if(ch == '[') {
                st.push(ch);
                mul *= 3;
            }

            else if(ch == ')') {
                if(st.empty() || st.peek() != '(') {
                    ans = 0;
                    break;
                }
                // () 인 경우
                if(str.charAt(i-1) == '(' && !st.empty()) {
                    ans += mul;
                }
                mul /= 2;
                st.pop();
            }

            else if(ch == ']') {
                if(st.empty() || st.peek() != '[') {
                    ans = 0;
                    break;
                }
                // [] 인 경우
                if(str.charAt(i-1) == '[' && !st.empty()) {
                    ans += mul;
                }
                mul /= 3;
                st.pop();
            }
        }

        if(!st.empty()) ans = 0;
        System.out.println(ans);
    }
}
