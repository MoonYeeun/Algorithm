package boj;

import java.util.*;

// 백준 1339 단어수학
// 1. 각 단어 당 자리수에 해당하는 알파벳 계산
// 2. 가중치 가장 큰 알파벳부터 (9 - 0)로 숫자 부여
public class Back_1339 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] word = new String[n];
        int[] alphabet = new int[26];

        for(int i = 0 ; i < n ; i++) {
            word[i] = sc.next();

            // 각 알파벳 당 가중치 부여
            int idx = word[i].length() - 1;
            for(int j = 0 ; j < word[i].length() ; j++) {
                alphabet[word[i].charAt(j) - 'A'] += (int) Math.pow(10, idx--);
            }
        }
        Arrays.sort(alphabet);

        int max = 9;
        int ans = 0;
        for(int i = 25 ; i >= 0 ; i--) {
            if(alphabet[i] == 0) continue;

            ans += alphabet[i] * max--;
        }
        System.out.println(ans);
    }
}
