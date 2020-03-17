package boj;

import java.util.Arrays;
import java.util.Scanner;

// 백준 1759 암호만들기
public class Back_1759 {
    public static int l,c;
    public static String[] str;
    public static String[] ansAry;
    public static String vowel = "aeiou";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        l = sc.nextInt();
        c = sc.nextInt();

        ansAry = new String[l+1];
        str = new String[c];

        for(int i = 0 ; i < c ; i++) {
            str[i] = sc.next();
        }

        Arrays.sort(str);
        dfs(0,0,0,0);
    }

    public static void dfs(int start, int depth, int cons, int vow) {
        if(depth == l) {
            if(cons >= 2 && vow >= 1)
                printAnswer();
            return;
        }
        for(int i = start; i < c ; i++) {
            ansAry[depth] = str[i];
            if(vowel.contains(ansAry[depth])){ // 모음
                dfs(i+1, depth+1, cons, vow+1);
            }
            else { // 자음
                dfs(i+1, depth+1, cons+1, vow);
            }
        }
    }
    public static void printAnswer() {
        for(int i = 0; i < l; i++) {
            System.out.print(ansAry[i]);
        }
        System.out.println();
    }
}
