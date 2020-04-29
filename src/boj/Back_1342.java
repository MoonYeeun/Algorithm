package boj;

import java.util.*;

// 백준 1342 행운의 문자열
// 중복 문자 factorial 로 나눠서 중복 제거하기 !
public class Back_1342 {
    static String s;
    static HashMap<Character, Integer> map = new HashMap<>();
    static int answer = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s = sc.next();
        // factorial 계산을 위해 중복된 문자 구하기
        for(int i = 0 ; i < s.length() ; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) +1);
        }
        per(0,  0, ""); // 만들 수 있는 문자개수
        Iterator<Character> it = map.keySet().iterator();
        while (it.hasNext()) {
            int cnt = map.get(it.next());
            // factorial 계산
            int fac = 1;
            for(int i = 1 ; i <= cnt ; i++) {
                fac *= i;
            }
            answer /= fac;
        }
        System.out.println(answer);
    }
    static void per(int cnt, int bit, String str) {
        if(cnt == s.length()) {
            answer++;
        }
        for(int i = 0 ; i < s.length() ; i++) {
            if((bit & (1<<i)) == (1<<i)) continue;
            if(str.length() != 0 && s.charAt(i) == str.charAt(str.length()-1)) continue;

            per(cnt+1,  bit | (1<<i), str + s.charAt(i));
        }
    }
}
