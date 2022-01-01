package LeetCode;

// ⭐️ 424. Longest Repeating Character Replacement
// 슬라이딩 윈도우
public class LC424_Longest_Repeating_Character_Replacement {
    public static void main(String[] args) {
        String s = "AABABBB";
        int k = 1;
//        String s = "ABAB";
//        int k = 2;
        int result = characterReplacement(s, k);
        System.out.println(result);
    }

    public static int characterReplacement(String s, int k) {
        int start = 0;
        int maxLen = 0;
        int mostFreq = 0;
        char[] lookUp = new char[26];

        for (int end = 0; end < s.length(); end++) {
            mostFreq = Math.max(mostFreq, ++lookUp[s.charAt(end) - 'A']);

            // 슬라이딩 윈도우의 개수 - 가장 많은 알파벳 개수 > k 개수 => 윈도우 크기 조정
            if (end - start + 1 - mostFreq > k) {
                lookUp[s.charAt(start++) - 'A']--;
            } else {
                maxLen = Math.max(end - start + 1, maxLen);
            }
        }

        return maxLen;
    }
}
