package LeetCode.LC5;

// ⭐️ 5. Longest Palindromic Substring
public class Longest_Palindromic_Substring {
    public static void main(String[] args) {
        String s = "babad";
        String result = longestPalindrome(s);
        System.out.println(result);
    }

    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;

        int start = 0;
        int end = 0;

        for (int i = 0; i < len; i++) {
            int odd = isPalindrome(s, i, i); // 기준점 i로부터 홀수개인 펠린드롬
            int even = isPalindrome(s, i, i + 1); // 기준점 i로부터 짝수개인 펠린드롬

            int max = Math.max(odd, even);

            if (max > end - start + 1) {
                start = i - (max - 1) / 2;
                end = i + (max / 2);
            }
        }
        return s.substring(start, end + 1);
    }

    public static int isPalindrome(String str, int l, int r) {
        while (l >= 0 && r < str.length() && str.charAt(l) == str.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }
}
