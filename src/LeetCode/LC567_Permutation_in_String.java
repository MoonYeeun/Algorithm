package LeetCode;

// 567. Permutation in String
public class LC567_Permutation_in_String {
    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidboaoo";

        boolean result = checkInclusion(s1, s2);
        System.out.println(result);
    }

    public static boolean checkInclusion(String s1, String s2) {
        int[] s1_alpha = new int[26];
        int[] s2_alpha = new int[26];

        for (char c : s1.toCharArray()) {
            s1_alpha[c - 'a']++;
        }

        int s = 0;
        int s1Len = s1.length();
        int s2Len = s2.length();

        for (int i = 0; i < s2Len; i++) {
            char end = s2.charAt(i);
            s2_alpha[end - 'a']++;

            if (i >= s1Len) {
                char start = s2.charAt(s++);
                s2_alpha[start - 'a']--;
            }

            if (isSame(s1_alpha, s2_alpha)) return true;
        }
        return false;
    }

    public static boolean isSame(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }
}

