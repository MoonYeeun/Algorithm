package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// 백준 9935 문자열 폭발
/*
그냥 문자열 치환(replace)로 풀면 메모리 초과 난다...
stack 으로 처리해야 함.
* */
public class Back_9935 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine(); //문자열
        String explosion = br.readLine(); // 폭발 문자

        int len = explosion.length();
        Stack<Character> stack = new Stack<>();

        for(int i = 0 ; i < str.length() ; i++) {
            stack.add(str.charAt(i));
            boolean flag = true;
            // 폭발 문자 끝과 같을 경우
            if(str.charAt(i) == explosion.charAt(len-1) && stack.size() >= len) {
                Stack<Character> temp = new Stack<>();

                for(int j = len-1 ; j >= 0 ; j--) {
                    if(stack.peek() != explosion.charAt(j)) {
                        flag = false;
                        break;
                    }
                    temp.add(stack.pop());
                }
                // 일치 하지 않을 경우 다시 넣어줌
                if(!flag) {
                    while (!temp.isEmpty()) {
                        stack.add(temp.pop());
                    }
                }
            }
        }

        if(stack.isEmpty()) System.out.println("FRULA");
        else {
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            System.out.println(sb.reverse().toString());
        }
    }
}
