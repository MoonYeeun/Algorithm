package boj;

import java.util.Scanner;

// 백준 1062 가르침
public class Back_1062 {
    static boolean[] visit = new boolean[26];
    static String[] words;
    static int n, k;
    static int answer = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 남극의 글자
        k = sc.nextInt(); // 가르칠 수 있는 글자

        if(k < 5) {
            System.out.println(0);
            return;
        } else if(k == 26) {
            System.out.println(n);
            return;
        }

        k = k - 5; // basic 에 포함된 글자 뺀 나머지 개수
        words = new String[n];

        String basic = "antic"; // 첫글자 마지막 글자에 포함된 단어 (무조건 알아야함)
        for(int i = 0 ; i < basic.length() ; i++) {
            visit[basic.charAt(i) - 'a'] = true;
        }

        for (int i = 0; i < n; i++) {
            String str = sc.next();
            str = str.substring(4, str.length() - 4); // anta - tica 뺀 나머지
            words[i] = str;
        }
        dfs(0, 0);
        System.out.println(answer);
    }
    static void dfs(int idx, int cnt) {
        if(cnt == k) {
            answer = Math.max(answer, check());
        }
        for(int i = idx ; i < 26 ; i++) {
            if(!visit[i]) {
                visit[i] = true;
                dfs(i, cnt + 1);
                visit[i] = false;
            }
        }
    }
    static int check() {
        int cnt = 0;
        for(int i = 0 ; i < words.length ; i++) {
            String word = words[i];
            boolean flag = true;
            for(int j = 0 ; j < word.length() ; j++) {
                if(!visit[word.charAt(j) - 'a']) flag = false;
            }
            if(flag) cnt++;
        }
        return cnt;
    }
}
