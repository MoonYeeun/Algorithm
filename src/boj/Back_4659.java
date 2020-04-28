package boj;

import java.util.*;

// 백준 4659 비밀번호 발음하기
public class Back_4659 {
    static String vowel = "a/e/i/o/u";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String str = sc.next();
            if(str.equals("end")) break;

            boolean flag = true;
            int vow = 0; // 모음 개수
            int cnt = 0; // 연속 개수
            char last = str.charAt(0); // 이전 글자

            for(int i = 0 ; i < str.length() ; i++) {
                boolean cur = isVow(String.valueOf(str.charAt(i))); // 모음 자음 판단
                if(cur) vow++;

                // 같은 문자 연속
                if(i != 0 && last == str.charAt(i) && last != 'e' && last != 'o') {
                    flag = false;
                    break;
                }
                // 모음 없는 경우
                if(i == str.length()-1 && vow == 0) flag = false;

                if(isVow(String.valueOf(last)) == cur) cnt++;
                else cnt = 1;

                // 자음 or 모음 연속 3번
                if(cnt >= 3) {
                    flag = false;
                    break;
                }
                last = str.charAt(i);
            }
            if(flag) System.out.println("<" + str + ">" + "is acceptable.");
            else System.out.println("<" + str + ">" + "is not acceptable.");
        }
    }
    static boolean isVow(String s) {
        if(vowel.contains(s))
            return true;
        return false;
    }
}
