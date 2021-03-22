package LeetCode;

import java.util.*;

// 3. Longest Substring Without Repeating Characters
public class LC3_Longest_Substring_Without_Repeating_Characters {
    public static void main(String[] args) {
        //String s = "abcabcbb";
        String s = "pwwkew";
        int result = lengthOfLongestSubstring(s);

        System.out.println(result);
    }

    public static int lengthOfLongestSubstring(String str) {
        HashMap<Character, Integer> map = new HashMap<>();

        int s = 0;
        int e = 0;
        int ans = 0;

        while (e < str.length()) {
            char cur = str.charAt(e);
            map.put(cur, map.getOrDefault(cur, 0) + 1);

            while (s <= e && map.get(cur) > 1) {
                char temp = str.charAt(s++);
                map.put(temp, map.get(temp) - 1);
            }

            ans = Math.max(ans, e - s + 1);
            e++;
        }

        return ans;
    }
}
