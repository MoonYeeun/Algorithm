package boj;

import java.util.*;

// 백준 1701 Cubeditor
// 하나씩 문자열 잘라가면서 KMP
public class Back_1701 {
    static int[] table;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        int ans = 0;

        while (true) {
            // 더 이상 비교할 필요 없는 경우
            if(str.length() <= ans || str.length() <= 1) {
                System.out.println(ans);
                break;
            }
            table = new int[str.length()];

            makeTable(str);

            str = str.substring(1); // 문자열 앞에서부터 자르기

            for(int i : table) {
                if(ans < i) ans = i; // 가장 긴 문자열
            }
        }
    }
    static void makeTable(String str) {
        int j = 0;
        for(int i = 1; i < str.length() ; i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = table[j-1];
            }

            if(str.charAt(i) == str.charAt(j)) table[i] = ++j;
        }
    }
}
