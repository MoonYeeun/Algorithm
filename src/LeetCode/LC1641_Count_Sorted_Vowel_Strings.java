package LeetCode;

// 1641. Count Sorted Vowel Strings
// dp
public class LC1641_Count_Sorted_Vowel_Strings {
    static int[][] dp;

    public static void main(String[] args) {
        int n = 2;
        System.out.println(countVowelStrings(n));
    }

    public static int countVowelStrings(int n) {
        dp = new int[n + 1][6];
        return check(0, n);
    }

    public static int check(int idx, int cnt) {
        if (cnt == 0) return 1;
        if (dp[cnt][idx] != 0) return dp[cnt][idx];

        for (int i = idx; i < 5; i++) {
            dp[cnt][idx] += check(i, cnt - 1);
        }
        return dp[cnt][idx];
    }
}
