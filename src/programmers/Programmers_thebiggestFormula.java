package programmers;

import java.util.*;

// 카카오 인턴 수식최대화
// 1. 연산자, 숫자 추출
// 2. 연산자 우선순위정하기
// 3. 계산 (우선순위 아닌 것은 스택에 넣기)
//  1) 해당 스택으로 다시 연산자, 숫자 배열 갱신
public class Programmers_thebiggestFormula {
    static ArrayList<String> list = new ArrayList<>();
    static String[] num, oper;
    static long ans;
    public static void main(String[] args) {
        String expression = "100-200*300-500+20";
        //String expression = "50*6-3*2";
        solution(expression);
    }
    public static long solution(String expression) {
        // expression에서 숫자 추출
        String result = expression.replaceAll("[^0-9]", "/");
        num = result.split("/");

        // expression에서 연산자 추출
        result = expression.replaceAll("[0-9]", "");
        oper = result.split("");

        // 연산자 우선순위 정하기
        String op = "+-*";
        makeOrder(op, 0, 0);

        return ans;
    }
    static void calPrize() {
        Stack<Long> numSt = new Stack<>();
        Stack<String> opSt = new Stack<>();

        long[] number = new long[num.length];
        for(int i = 0 ; i < num.length ; i++) {
            number[i] = Long.parseLong(num[i]);
        }
        String[] operator = new String[oper.length];
        for(int i = 0 ; i < oper.length ; i++) {
            operator[i] = oper[i];
        }

        for(int i = 0 ; i < 3 ; i++) {
            numSt.push(number[0]);

            for(int j = 0 ; j < operator.length ; j++) {
                numSt.push(number[j+1]);

                // 해당 연산자가 현재 우선순위 연산자인 경우
                if(operator[j].equals(list.get(i))) {
                    long a = numSt.pop();
                    long b = numSt.pop();
                    numSt.push(cal(b, a, operator[j]));
                }
                // 아닌 경우
                else opSt.push(operator[j]);
            }
            // number 배열 갱신
            long[] temp1 = new long[numSt.size()];
            int idx = numSt.size()-1;
            while (!numSt.isEmpty()) {
                temp1[idx--] = numSt.pop();
            }
            // oper 배열 갱신
            String[] temp2 = new String[opSt.size()];
            idx = opSt.size()-1;
            while (!opSt.isEmpty()) {
                temp2[idx--] = opSt.pop();
            }

            number = temp1;
            operator = temp2;
        }

        ans = Math.max(ans, Math.abs(number[0]));
    }
    static void makeOrder(String op, int cnt, int bit) {
        if(cnt == 3) {
            calPrize();
        }

        for(int i = 0 ; i < 3 ; i++) {
            if((bit & (1<<i)) == (1<<i)) continue;

            list.add(String.valueOf(op.charAt(i)));
            makeOrder(op, cnt + 1, bit | (1<<i));
            list.remove(list.size()-1);
        }
    }
    static long cal(long a, long b, String op) {
        if(op.equals("+")) return a + b;
        else if(op.equals("-")) return a - b;
        else return a * b;
    }
}
