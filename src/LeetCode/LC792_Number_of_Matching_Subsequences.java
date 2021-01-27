package LeetCode;

import java.util.*;

// 792. Number of Matching Subsequences
public class LC792_Number_of_Matching_Subsequences {
    public static void main(String[] args) {
        String S = "abcde";
        String[] words = {"a", "bb", "acd", "ace"};

        int ans = numMatchingSubseq(S, words);
        System.out.println(ans);
    }

    public static int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        HashSet<String> set = new HashSet<>();

        for (String word : words) {
            int idx = 0;
            int s_idx = -1;

            if (set.contains(word)) {
                ans++;
                continue;
            }
            while (idx < word.length()) {
                s_idx = S.indexOf(word.charAt(idx), s_idx + 1);

                if (s_idx == -1) break;
                idx++;
            }

            if (idx == word.length()) {
                ans++;
                set.add(word);
            }
        }
        return ans;
    }

    // 시간 줄인 코드 (다른 사람 코드 참고 🤩)
//    public int numMatchingSubseq(String s, String[] words) {
//        HashMap<Character, Queue<String>> map = new HashMap<>();
//
//        for (char c = 'a'; c <= 'z'; c++) {
//            map.putIfAbsent(c,new LinkedList<>());
//        }
//
//        for (String word : words) {
//            map.get(word.charAt(0)).add(word);
//        }
//
//        int ans = 0;
//
//        for (char c : s.toCharArray()) {
//            Queue<String> queue = map.get(c);
//
//            int size = queue.size();
//
//            for (int i = 0 ; i < size ; i++) {
//                String temp = queue.poll();
//
//                if (temp.length()==1) ans++;
//                else map.get(temp.charAt(1)).add(temp.substring(1));
//            }
//        }
//        return ans;
//    }
}
