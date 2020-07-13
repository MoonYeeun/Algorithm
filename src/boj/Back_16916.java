package boj;

import java.util.*;

// 백준 16916 부분문자열
// KMP
public class Back_16916 {
    static int[] table;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String p = sc.next();

        table = new int[p.length()];
        makeTable(p);

        int cnt = 0;
        int j = 0;
        for(int i = 0 ; i < s.length() ; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j)) {
                j = table[j-1];
            }
            if(s.charAt(i) == p.charAt(j)) {
                if(j == p.length()-1) {
                    cnt++;
                    j = table[j];
                }
                else j++;
            }
        }
        if(cnt > 0) System.out.println(1);
        else System.out.println(0);
    }
    static void makeTable(String p) {
        int j = 0;
        for(int i = 1 ; i < p.length() ; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) {
                j = table[j-1];
            }

            if(p.charAt(i) == p.charAt(j)) {
                table[i] = ++j;
            }
        }
    }
}
