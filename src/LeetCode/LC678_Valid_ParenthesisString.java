package LeetCode;

import java.util.*;

// 스택 2개 (스택엔 idx값 저장)
public class LC678_Valid_ParenthesisString {
    public static void main(String[] args) {
        //String input = "()";
        //String input = "(*)";
        //String input = "(*))";
        //String input = "(((******))";
        String input = "(())((())()()(*)(*()(())())())()()((()())((()))(*";
        boolean result = checkValidString(input);
        System.out.println(result);
    }
    static public boolean checkValidString(String s) {
        char[] arr = s.toCharArray();
        Stack<Integer> bracket = new Stack<>();
        Stack<Integer> extra = new Stack<>();

        for(int i = 0 ; i < arr.length ; i++) {
            char cur = arr[i];

            if(cur == '(') bracket.push(i);
            else if(cur == '*') extra.push(i);
            else {
                if(bracket.size() == 0 && extra.size() == 0) return false;
                else if(bracket.size() == 0) extra.pop();
                else bracket.pop();
            }
        }

        while (bracket.size() > 0 && extra.size() > 0) {
            if(bracket.peek() > extra.peek()) break;

            bracket.pop();
            extra.pop();
        }
        return bracket.size() == 0;
    }
}
