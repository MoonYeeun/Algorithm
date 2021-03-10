package LeetCode;

// 44. Wildcard Matching
// dp (top-down)
public class LC44_Wildcard_Matching {
    static int[][] memo;

    public static void main(String[] args) {
//        String s = "cb";
//        String p = "?a";
//        String s = "";
//        String p = "******";
        String s = "a";
        String p = "aa";
//        String s = "acdcb";
//        String p = "a*c?b";
        boolean result = isMatch(s, p);
        System.out.println(result);
    }

    public static boolean isMatch(String s, String p) {
        memo = new int[s.length()][p.length()];
        return matching(0, 0, s, p);
    }

    public static boolean matching(int sIdx, int pIdx, String s, String p) {
        if (s.length() == sIdx) {
            if (p.length() == pIdx) return true;

            for (int i = pIdx; i < p.length(); i++) {
                if (p.charAt(i) != '*') return false;
            }
            return true;
        }
        if (pIdx >= p.length()) return false;

        char curP = p.charAt(pIdx);
        char curS = s.charAt(sIdx);

        if (memo[sIdx][pIdx] == 0) {
            // p가 * 인 경우
            if (curP == '*') {
                if (matching(sIdx, pIdx + 1, s, p) || matching(sIdx + 1, pIdx, s, p)
                        || matching(sIdx + 1, pIdx + 1, s, p)) memo[sIdx][pIdx] = 1;
                else memo[sIdx][pIdx] = -1;
            }
            // p가 ? 인 경우 이거나 p가 알파벳인 경우
            else if (curP == '?' || curP == curS)
                memo[sIdx][pIdx] = matching(sIdx + 1, pIdx + 1, s, p) ? 1 : -1;
        }
        return memo[sIdx][pIdx] == 1;
    }
}
