package LeetCode;

import java.util.HashSet;

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
}
