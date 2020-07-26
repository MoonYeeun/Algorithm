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
            if(str.length() <= ans) {
                System.out.println(ans);
                break;
            }
            table = new int[str.length()];

            StringBuilder sb = new StringBuilder();
            sb.append(str);
            makeTable(sb.reverse().toString());

            str = str.substring(0, str.length()-1);

            for(int i : table) {
                if(ans < i) ans = i;
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
