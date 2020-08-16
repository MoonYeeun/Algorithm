package boj;

import java.io.*;

// 백준 마법의단어
// kmp ver
public class Back_1097_1 {
    static int n, k, ans;
    static int[] table;
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
        table = new int[str.length()];
        // 실패테이블 만들기
        int j = 0;

        for(int i = 1 ; i < str.length() ; i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) j = table[j-1];

            if(str.charAt(i) == str.charAt(j)) table[i] = ++j;
        }

        // kmp
        String temp = str + str;
        int cnt = 0; j = 0;

        for(int i = 0 ; i < temp.length()-1 ; i++) {
            while (j > 0 && temp.charAt(i) != str.charAt(j)) j = table[j-1];

            if(temp.charAt(i) == str.charAt(j)) {
                if(j == str.length()-1) {
                    cnt++;
                    j = table[j];
                }
                else j++;
            }
        }
        return cnt == k ? 1 : 0;
    }
}
