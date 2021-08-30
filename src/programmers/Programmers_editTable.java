package programmers;

import java.util.Stack;

// 프로그래머스 2021 카카오 인턴십 - 표 편집
public class Programmers_editTable {
    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        //String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        String result = solution(n, k, cmd);
        System.out.println(result);
    }

    public static String solution(int n, int k, String[] cmd) {
        Stack<Integer> st = new Stack<>();

        int idx = k; // 현재 선택된 행
        int size = n; // 표 사이즈

        for (String cur : cmd) {
            String[] input = cur.split(" ");

            if (input[0].equals("U")) {
                idx -= Integer.parseInt(input[1]);
            } else if (input[0].equals("D")) {
                idx += Integer.parseInt(input[1]);
            } else if (input[0].equals("C")) {
                st.add(idx);
                size--;
                if (idx == size) idx--;
            } else {
                if (st.pop() <= idx) idx++;
                size++;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (size-- > 0) {
            sb.append("O");
        }
        while (!st.isEmpty()) {
            sb.insert(st.pop(), "X");
        }

        return sb.toString();
    }
}
