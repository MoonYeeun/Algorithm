package LeetCode;

// 1004. Max Consecutive Ones III
// sliding window
public class LC1004_Max_Consecutive_Ones_3 {
    public static void main(String[] args) {
        int[] A = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int K = 2;

        int result = longestOnes(A, K);
        System.out.println(result);
    }

    public static int longestOnes(int[] A, int K) {
        int s = 0;
        int cnt = 0;
        int ans = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) cnt++;

            while (s <= i && cnt > K) {
                if (A[s++] == 0) cnt--;
            }

            ans = Math.max(ans, i - s + 1);
        }
        return ans;
    }
}
