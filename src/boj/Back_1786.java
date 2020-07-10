package boj;

import java.util.*;

// 백준 1786 찾기
// KMP
public class Back_1786 {
    static int size;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String t = sc.nextLine();
        String p = sc.nextLine();

        size = p.length();
        int[] table = makeTable(p); // 패턴 문자열 실패테이블 만들기

        // Kmp 로 비교
        int cnt = 0;
        ArrayList<Integer> list = new ArrayList<>();
        int j = 0;
        for(int i = 0 ; i < t.length() ; i++) {
            while (j > 0 && t.charAt(i) != p.charAt(j)) {
                j = table[j-1];
            }
            if(t.charAt(i) == p.charAt(j)) {
                // 패턴 일치
                if(j == size -1) {
                    cnt++;
                    list.add(i - size + 2);
                    j = table[j];
                }
                else j++;
            }
        }
        System.out.println(cnt);
        for(int i : list) {
            System.out.print(i + " ");
        }
    }
    static int[] makeTable(String str) {
        int[] table = new int[size];

        int j = 0;
        for(int i = 1 ; i < size ; i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = table[j-1];
            }
            if(str.charAt(i) == str.charAt(j)) {
                table[i] = ++j;
            }
        }

        return table;
    }
}
