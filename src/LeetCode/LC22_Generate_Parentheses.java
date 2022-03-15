package LeetCode;

import java.util.ArrayList;
import java.util.List;

// 22. Generate Parentheses
public class LC22_Generate_Parentheses {
    static List<String> ans;

    public static void main(String[] args) {
        int n = 3;
        var result = generateParenthesis(n);
        for (String s : result) {
            System.out.println(s);
        }
    }

    static public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        generate(1, n - 1, n, "(");
        return ans;
    }

    static public void generate(int openBracket, int openCnt, int closeCnt, String str) {
        if (closeCnt == 0) {
            ans.add(str);
            return;
        }
        // 열린 괄호가 존재하는 경우
        if (openBracket > 0) {
            // 닫힌 괄호 가능
            generate(openBracket - 1, openCnt, closeCnt - 1, str + ")");
        }
        // 열린 괄호 사용가능한 경우
        if (openCnt > 0) {
            generate(openBracket + 1, openCnt - 1, closeCnt, str + "(");
        }
    }
}
