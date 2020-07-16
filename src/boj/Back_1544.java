package boj;

import java.util.*;

// 백준 1544 사이클단어
public class Back_1544 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String[] word = new String[n];
        for(int i = 0 ; i < n ; i++) {
            word[i] = sc.next();
        }

        boolean[] visit = new boolean[n];

        int ans = 0;
        for(int i = 0 ; i < n ; i++) {
            if(visit[i]) continue;

            visit[i] = true;
            ans++;

            for(int j = 0 ; j < n ; j++) {
                if(i == j || visit[j]) continue;
                // 길이 다르면 넘어감
                if(word[i].length() != word[j].length()) continue;

                if(check(word[i], word[j])) { // 같은 단어인지 체크
                    visit[j] = true;
                }
            }
        }
        System.out.println(ans);
    }
    static boolean check(String word1, String word2) {
        String temp = word1 + word1;

        if(temp.contains(word2)) return true;
        else return false;
    }
}
