package boj;

import java.util.*;

// 백준 1141 접두사
// 정렬 & 모든 접두사 만들어 놓기
public class Back_1141 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = sc.next();
        }

        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String w1, String w2) {
                return w2.length() - w1.length();
            }
        });

        HashSet<String> set = new HashSet<>();
        int ans = 0;
        for (String word : words) {
            if (set.contains(word)) continue;

            ans++;
            for (int i = 1; i <= word.length(); i++) {
                set.add(word.substring(0, i));
            }
        }
        System.out.println(ans);
    }
}

