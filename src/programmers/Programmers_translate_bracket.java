package programmers;

import java.util.Stack;

public class Programmers_translate_bracket {
    public static void main(String[] args) {
        String p = "(()())()";

        System.out.println(strCheck(p));
    }
    static String strCheck(String str) {
        int len = str.length();
        String u = "";
        String v = "";
        String answer = "";
        // 빈 문자열인 경우
        if(str.isEmpty())
            return str;

        char past_str = str.charAt(0); // 이전 문자열을 기억할 변수
        Stack<Character> stack = new Stack<>();
        stack.add(str.charAt(0));
        for(int i = 1 ; i < len ; i++) {
            if (!stack.isEmpty() && past_str != str.charAt(i)) {
                stack.pop();
                past_str = (str.charAt(i) == '(') ? ')' : '(';
            } else if (past_str == str.charAt(i))
                stack.add(str.charAt(i));

            if (stack.isEmpty()) {
                u = str.substring(0, i+1);
                if(i+1 < len) v = str.substring(i+1, len);
                break;
            }
        }

        if(goodStr_Check(u)) {
            answer += u + strCheck(v);
            return answer;
        } else {
            String temp = "(" + strCheck(v) + ")"; // 4-1 ~ 4-3 과정
            u = u.substring(1,u.length()-1); // u의 첫 번째와 마지막 문자를 제거

            StringBuilder sb = new StringBuilder(u);
            for(int i = 0 ; i < u.length() ; i++) {
                if(u.charAt(i) == '('){
                    sb.setCharAt(i, ')');
                    u = sb.toString();
                }
                else {
                    sb.setCharAt(i, '(');
                    u = sb.toString();
                }
            }
            answer += temp + u;
            return answer;
        }
    }
    // 올바른 문자열인지 체크하는 함수
    static boolean goodStr_Check(String str) {
        Stack<Character> check = new Stack<>();
        int str_len = str.length();
        for(int i = 0 ; i < str_len ; i++) {
            if(str.charAt(i) == '(')
                check.add(str.charAt(i));
            if(check.isEmpty() && str.charAt(i) == ')')
                return false;
            else if(!check.isEmpty() && str.charAt(i) == ')')
                check.pop();
        }
        if(check.isEmpty()) return true;
        else return false;
    }
}
