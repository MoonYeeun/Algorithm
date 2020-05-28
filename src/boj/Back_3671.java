package boj;

import java.util.*;

// 백준 3671 산업스파이의 편지
// 1. 에라토스테네스의 체로 소수 판
// 2. 순열 만들어서 중복되지 않은 소수인지 체크
public class Back_3671 {
    static int c, len, answer;
    static String num;
    static boolean[] isNotPrime = new boolean[10000000];
    static HashSet<Integer> set;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();

        // 소수 판별
        isNotPrime[0] = isNotPrime[1] = true;
        for(int i = 2 ; i < 10000000 ; i++) {
            if(isNotPrime[i]) continue;

            for(int j = i * 2; j < 10000000 ; j+=i) {
                isNotPrime[j] = true;
            }
        }

        for(int i = 0 ; i < c ; i++) {
            num = sc.next();
            len = num.length();
            answer = 0;

            set = new HashSet<>();
            check(0, 0, 0);
            System.out.println(answer);
        }
    }
    static void check(int cnt, int bit, int n) {
        if(cnt > len) return;

        if(!set.contains(n) && !isNotPrime[n]) {
            answer++;
            set.add(n);
        }

        for(int i = 0 ; i < len ; i++) {
            if((bit & (1<<i)) == (1<<i)) continue;

            int temp = Integer.parseInt(String.valueOf(n) + num.charAt(i));
            check(cnt + 1, bit | (1<<i), temp);
        }
    }
}
