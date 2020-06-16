package boj;

import java.util.*;

// 백준 9997 폰트
// 비트마스킹
public class Back_9997 {
    static int[] word;
    static int n, answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        word = new int[n]; // 각 단어의 알파벳 비트로 표현
        for(int i = 0 ; i < n ; i++) {
            String str = sc.next();

            for(int j = 0 ; j < str.length() ; j++) {
                word[i] |= (1<< (str.charAt(j) - 'a'));
            }
        }
        pick(0, 0);
        System.out.println(answer);
    }
    static void pick(int idx, int bit) {
        if(idx == n) {
            if(bit == (1<<26) - 1) answer++;
            return;
        }
        if(idx >= n) return;
        // 단어 고르는 경우
        pick(idx + 1, bit | word[idx]);
        // 안고르는 경우
        pick(idx + 1, bit);
    }
}
