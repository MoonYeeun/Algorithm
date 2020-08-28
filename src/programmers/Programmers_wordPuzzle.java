package programmers;

// 프로그래머스 단어퍼즐
// dp
// 주어진 단어의 각 인덱스마다 strs 배열와 같은 substring 있는지 판단
// t.substring(idx - str.length + 1, idx+1) == str 이면 dp 계산
public class Programmers_wordPuzzle {
    public static void main(String[] args) {
        String[] strs = {"ba", "na", "n", "a"};
        String t = "banana";
//        String[] strs = {"app","ap","p","l","e","ple","pp"};
//        String t = "apple";
//        String[] strs = {"ba","an","nan","ban","n"};
//        String t = "banana";
        System.out.println(solution(strs, t));
    }

    static int solution(String[] strs, String t) {
        int[] dp = new int[t.length()];

        for (int i = 0; i < t.length(); i++) {
            for (String s : strs) {
                if (i + 1 - s.length() < 0) continue;

                if (t.substring(i - s.length() + 1, i + 1).equals(s)) {
                    if (i - s.length() + 1 == 0) {
                        dp[i] = 1;
                        continue;
                    }
                    if (dp[i - s.length()] > 0) {
                        dp[i] = dp[i] == 0 ? dp[i - s.length()] + 1 :
                                Math.min(dp[i], dp[i - s.length()] + 1);
                    }
                }
            }
        }
        return dp[t.length() - 1] == 0 ? -1 : dp[t.length() - 1];
    }
}
