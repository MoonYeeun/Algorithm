package programmers;

import java.util.*;

// 카카오 인턴 수식최대화
// 1. 연산자, 숫자 추출
// 2. 연산자 우선순위정하기
// 3. 계산
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

        ArrayList<Long> number = new ArrayList<>();
        for(int i = 0 ; i < num.length ; i++) {
            number.add(Long.parseLong(num[i]));
        }

        ArrayList<String> operator = new ArrayList<>();
        for(int i = 0 ; i < oper.length ; i++) {
            operator.add(oper[i]);
        }

        // 우선순위 연산자인 경우:
        // 1. 숫자리스트에서 계산할 숫자 제거 후 계산한 결과 다시 해당 인덱스에 맞는 리스트에 넣어줌
        // 2. 연산자리스트에서 해당 연산자 제거
        for(int i = 0 ; i < 3 ; i++) {
            for(int idx = 0 ; idx < operator.size() ; idx++) {
                // 해당 연산자가 현재 우선순위 연산자인 경우
                if(operator.get(idx).equals(list.get(i))) {
                    long a = number.remove(idx);
                    long b = number.remove(idx);
                    number.add(idx, cal(a, b, operator.get(idx)));

                    operator.remove(idx--);
                }
            }
        }

        ans = Math.max(ans, Math.abs(number.get(0)));
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
