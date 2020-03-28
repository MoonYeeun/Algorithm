package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// 백준 2504 괄호의 값
/*
스택이 비어있지 않은 경우
괄호로 만들어지는 값 () = 2 [] = 3 을 다시 스택에 넣어주기

올바른 괄호 만들어 지기 전까지 숫자가 스택에 있다면
값 더하고 마지막에 올바른 괄호 값 나온다면 곱하기
* */
public class Back_2504 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String bracket = br.readLine(); // 괄호

        int answer = 0;
        Stack<String> stack = new Stack<>();

        for(int i = 0 ; i < bracket.length() ; i++) {
            int temp = 0;

            if(bracket.charAt(i) == '(' || bracket.charAt(i) == '[')
                stack.add(Character.toString(bracket.charAt(i)));

            if((bracket.charAt(i) == ')' || bracket.charAt(i) == ']') && stack.isEmpty()) {
                answer = 0;
                break;
            }
            if(bracket.charAt(i) == ')') {
                // 바로 올바른 괄호 나오는 경우
                if(stack.peek().equals("(")) {
                    stack.pop();
                    if(stack.isEmpty()) answer += 2;
                    else stack.add(String.valueOf(2));
                    continue;
                }
                // 숫자도 아닌 경우 -> 잘못된 괄호
                if(!Character.isDigit(stack.peek().charAt(0))) {
                    answer = 0;
                    break;
                }
                // 숫자 존재하는 경우
                while (!stack.isEmpty()) {
                    String str = stack.peek();

                    if(str.equals("(")) {
                        stack.pop();
                        temp *= 2;
                        if(stack.isEmpty()) answer += temp;
                        else stack.add(String.valueOf(temp));
                        break;
                    }
                    else if (Character.isDigit(str.charAt(0)))
                        temp += Integer.parseInt(stack.pop());

                    else {
                        answer = 0;
                        break;
                    }
                }
            }
            if(bracket.charAt(i) == ']') {
                // 바로 올바른 괄호 나오는 경우
                if(stack.peek().equals("[")) {
                    stack.pop();
                    if(stack.isEmpty()) answer += 3;
                    else stack.add(String.valueOf(3));
                    continue;
                }
                // 숫자도 아닌 경우 -> 잘못된 괄호
                if(!Character.isDigit(stack.peek().charAt(0))) {
                    answer = 0;
                    break;
                }
                // 숫자 존재하는 경우
                while (!stack.isEmpty()) {
                    String str = stack.peek();

                    if(str.equals("[")) {
                        stack.pop();
                        temp *= 3;
                        if(stack.isEmpty()) answer += temp;
                        else stack.add(String.valueOf(temp));
                        break;
                    }
                    else if (Character.isDigit(str.charAt(0)))
                        temp += Integer.parseInt(stack.pop());

                    else {
                        answer = 0;
                        break;
                    }
                }
            }
        }

        if(!stack.isEmpty()) System.out.println(0);
        else System.out.println(answer);
    }
}
