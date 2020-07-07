package boj;

import java.util.*;

// 백준 2800 괄호제거
public class Back_2800 {
    static String s;
    static int[] bracket;
    static boolean checked[];
    static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s = sc.next();
        bracket = new int[s.length()]; // 올바른 괄호 쌍 인덱스 구하기
        checked = new boolean[s.length()];

        Stack<Integer> st = new Stack<>();
        for(int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);

            if(c == '(') {
                st.push(i);
            }
            else if(c == ')') bracket[i] = st.pop();
        }

        recur(0, "");

        set.remove(s);
        ArrayList<String> list = new ArrayList<>(set);
        Collections.sort(list);

        for(String str : list) {
            System.out.println(str);
        }
    }
    static void recur(int idx, String str) {
        if(idx >= s.length()) {
            set.add(str);
            return;
        }
        char cur = s.charAt(idx);

        if(cur == '(') {
            // 괄호 제거
            checked[idx] = true;
            recur(idx + 1, str);
            checked[idx] = false;

            // 제거 안함
            recur(idx + 1, str + cur);
        }
        else if(cur == ')') {
            // 해당 괄호가 제거된 경우
            if(checked[bracket[idx]]) recur(idx + 1,str);
            else recur(idx + 1, str + cur);
        }
        else recur(idx + 1, str + cur);
    }
}
