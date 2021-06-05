package boj;

import java.util.*;

// 백준 5397 키로거
// 스택
public class Back_5397 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        while (n-- > 0) {
            String input = sc.next();
            System.out.println(findPassword(input));
        }
    }

    static String findPassword(String input) {
        char[] arr = input.toCharArray();
        Stack<Character> result = new Stack<>();
        Stack<Character> temp = new Stack<>();

        for (char c : arr) {
            if (c == '<') {
                if (!result.empty()) temp.add(result.pop());
                continue;
            }
            if (c == '>') {
                if (!temp.empty()) result.add(temp.pop());
                continue;
            }
            if (c == '-') {
                if (!result.empty()) result.pop();
                continue;
            }
            result.add(c);
        }

        while (!temp.empty()) {
            result.add(temp.pop());
        }

        StringBuilder sb = new StringBuilder();
        while (!result.empty()) {
            sb.append(result.pop());
        }
        return sb.reverse().toString();
    }
}
