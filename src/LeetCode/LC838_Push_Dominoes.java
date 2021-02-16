package LeetCode;

import java.util.*;

public class LC838_Push_Dominoes {
    public static void main(String[] args) {
        String dominoes = ".L.R...LR..L..";
        System.out.println(pushDominoes(dominoes));
    }

    public static String pushDominoes(String dominoes) {
        int len = dominoes.length();
        char[] result = new char[len];
        Arrays.fill(result, '.');

        StringBuilder sb = new StringBuilder();
        for (char c : result) {
            sb.append(c);
        }
        return sb.toString();
    }
}
