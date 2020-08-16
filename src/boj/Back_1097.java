package boj;

import java.io.*;

// 백준 1097 마법의단어
// 문자열 (kmp가 정해인 것 같지만 kmp로 안풀어도 풀림..)
public class Back_1097 {
    static int n, k, ans;
    static String[] word;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        word = new String[n];
        for(int i = 0 ; i < n ; i++) {
            word[i] = br.readLine();
        }
        k = Integer.parseInt(br.readLine());

        makeWord(0, 0, "");

        System.out.println(ans);
    }
    static void makeWord(int cnt, int bit, String str) {
        if(cnt == n) {
            ans += check(str);
            return;
        }

        for(int i = 0 ; i < n ; i++) {
            if((bit & (1<<i)) == (1<<i)) continue;

            makeWord(cnt +1, bit | (1<<i), str + word[i]);
        }
    }
    static int check(String str) {
        int cnt = 0;

        String temp = str + str;
        int idx = temp.indexOf(str);

        while (idx < str.length() && idx != -1) {
            cnt++;
            idx = temp.indexOf(str, idx+1);
        }

        return cnt == k ? 1 : 0;
    }
}
