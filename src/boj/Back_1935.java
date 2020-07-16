package boj;

import java.util.*;

// 백준 1935 후위표기식2
public class Back_1935 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String formula = sc.next();

        String alpha = "0ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 1 ; i <= n ; i++) {
            map.put(alpha.charAt(i), sc.nextInt());
        }

        Stack<Double> st = new Stack<>();
        for(int i = 0 ; i < formula.length() ; i++) {
            char c = formula.charAt(i);

            // 알파벳인 경우
            if(Character.isAlphabetic(c)) {
                st.push(map.get(c) * 1.0);
            }
            // 연산자인 경우
            else {
                double num2 = st.pop();
                double num1 = st.pop();

                double result = cal(num1, num2, c);
                st.push(result);
            }
        }

        System.out.println(String.format("%.2f", st.pop()));
    }
    static double cal(double a, double b, char op) {
        if(op == '+') return a + b;
        else if(op == '-') return a - b;
        else if(op == '*') return a * b;
        else return a * 1.0 / b;
    }
}
