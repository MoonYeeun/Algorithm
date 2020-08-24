package boj;

import java.io.*;
import java.util.*;

// 백준 19591 독특한 계산기
// 문자열 파싱 & 구현
// split[정규표현식]이 성능상 좋지 않나보다... 시간초과의 늪...
public class Back_19591 {
    static LinkedList<Long> numbers = new LinkedList<>();
    static LinkedList<String> op = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        // 숫자 / 연산자 구분
        boolean flag = false;
        String num = "";

        for(int i = 0 ; i < str.length() ; i++) {
            char c = str.charAt(i);

            if(i == 0 && c == '-') {
                flag = true;
                continue;
            }

            if (Character.isDigit(c)) num += c;
            else {
                op.add(String.valueOf(c));
                if(flag) {
                    numbers.add(-Long.parseLong(num));
                    flag = false;
                }
                else numbers.add(Long.parseLong(num));
                num = "";
            }
        }
        if(flag) numbers.add(-Long.parseLong(num));
        else numbers.add(Long.parseLong(num));

        doing();
        System.out.println(numbers.getFirst());
    }
    static void doing() {
        while (op.size() != 0) {
            if(op.size() >= 2) {
                String firstOP = op.getFirst();
                String lastOP = op.getLast();

                long result1 = cal(numbers.get(0), numbers.get(1), firstOP);
                long result2 = cal(numbers.get(numbers.size()-2), numbers.get(numbers.size()-1), lastOP);

                // 우선순위 별 계산 수행
                if(checkOP(firstOP) < checkOP(lastOP) || ((checkOP(firstOP) == checkOP(lastOP)) && result1 >= result2)) {
                    numbers.pollFirst();
                    numbers.pollFirst();
                    numbers.addFirst(result1);
                    op.pollFirst();
                }
                else if(checkOP(firstOP) > checkOP(lastOP) || ((checkOP(firstOP) == checkOP(lastOP)) && result1 < result2)) {
                    numbers.pollLast();
                    numbers.pollLast();
                    numbers.addLast(result2);
                    op.pollLast();
                }
            }
            else {
                String OP = op.pollFirst();
                long v1 = numbers.pollFirst();
                long v2 = numbers.pollFirst();

                numbers.add(0, cal(v1, v2, OP));
            }
        }
    }
    static long cal(long v1, long v2, String op) {
        if(op.equals("+")) return v1 + v2;
        else if(op.equals("-")) return v1 - v2;
        else if(op.equals("*")) return v1 * v2;
        else return v1 / v2;
    }
    static int checkOP(String OP) {
        if(OP.equals("*") || OP.equals("/")) return 1;
        else return 2;
    }
}
