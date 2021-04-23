package boj;

import java.util.*;

public class Back_17609 {
    static char[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        while (n-- > 0) {
            String cur = sc.next();
            arr = cur.toCharArray();
            System.out.println(isPalindrome(0, arr.length - 1, false));
        }
    }

    public static int isPalindrome(int left, int right, boolean skip) {
        if (left > right) return skip ? 1 : 0;

        var result = 2;
        if (arr[left] == arr[right]) {
            return isPalindrome(left + 1, right - 1, skip);
        }

        if (!skip) {
            return Math.min(isPalindrome(left + 1, right, true), isPalindrome(left, right - 1, true));
        }
        return result;
    }
}
