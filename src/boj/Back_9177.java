package boj;

import java.util.*;

// 백준 1977 단어섞기
// 단어1, 단어2 에 없는 알파벳 조합 단어3에 있는지 체크 하여 시간 줄이기
public class Back_9177 {
    static String w1, w2, w3;
    static int w1_len, w2_len, w3_len;
    static boolean flag;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i = 1 ; i <= n ; i++) {
            flag = false;
            w1 = sc.next();
            w2 = sc.next();
            w3 = sc.next();

            w1_len = w1.length();
            w2_len = w2.length();
            w3_len = w3.length();
            // w3 에 있는 알파벳이 w1, w2 에 있는지 확인
            if(!check()) {
                System.out.println("Data set " + i + ": no");
                continue;
            }
            solve(0, 0, 0);
            if(flag) System.out.println("Data set " + i + ": yes");
            else System.out.println("Data set " + i + ": no");
        }
    }
    static void solve(int w1_idx, int w2_idx, int w3_idx) {
        if(flag) return;

        if(w3_idx == w3_len) {
            flag = true;
            return;
        }

        if(w1_idx < w1_len && w1.charAt(w1_idx) == w3.charAt(w3_idx))
            solve(w1_idx + 1, w2_idx, w3_idx + 1);
        if(w2_idx < w2_len && w2.charAt(w2_idx) == w3.charAt(w3_idx))
            solve(w1_idx, w2_idx + 1, w3_idx + 1);
    }
    static boolean check() {
        int idx = w1_len > w2_len ? w1_len : w2_len;
        HashSet<Character> set = new HashSet<>();

        for(int i = 0 ; i < idx ; i++) {
            if(i < w1_len) set.add(w1.charAt(i));
            if(i < w2_len) set.add(w2.charAt(i));
        }
        for(int i = 0 ; i < w3_len ; i++) {
            if(!set.contains(w3.charAt(i))) return false;
        }
        return true;
    }
}
