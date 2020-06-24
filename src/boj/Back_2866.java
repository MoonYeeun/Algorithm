package boj;

import java.util.*;

// 백준 2866 문자열잘라내기
// 이분탐색
public class Back_2866 {
    static int r, c;
    static String[] word;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();

        word = new String[r];

        for(int i = 0 ; i < r ; i++) {
            word[i] = sc.next();
        }

        int count = 0;
        int s = 1; int e = r-1;

        while (s <= e) {
            int mid = (s + e) / 2; // 검사할 행
            // 중복 체크
            if(check(mid)) {
                s = mid + 1;
                count = mid;
            }
            else e = mid - 1;
        }
        System.out.println(count);
    }
    static boolean check(int mid) {
        HashSet<String> set = new HashSet<>();

        for(int i = 0 ; i < c ; i++) {
            StringBuilder sb = new StringBuilder();

            for(int j = mid ; j < r ; j++) {
                sb.append(word[j].charAt(i));
            }
            if(set.contains(sb.toString())) return false;
            set.add(sb.toString());
        }
        return true;
    }
}
