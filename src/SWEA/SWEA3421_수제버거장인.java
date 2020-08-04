package SWEA;

import java.util.*;

// 조합
public class SWEA3421_수제버거장인 {
    static int n, m;
    static HashSet<Integer> notBurger;
    static HashSet<Integer> burger;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();
            m = sc.nextInt();

            notBurger = new HashSet<>();
            burger = new HashSet<>();

            for(int i = 0 ; i < m ; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                int bit = (1<<a) | (1<<b);
                notBurger.add(bit);
            }
            com(1, 0);
            System.out.println("#" + test_case + " " + burger.size());
        }
    }
    static void com(int idx, int bit) {
        if(idx == n+1) {
            burger.add(bit);
            return;
        }
        // 선택 0 -> 불가능한 조합인지 판단
        if(check(bit | (1<<idx))) com(idx + 1, bit | (1<<idx));
        // 선택 x
        com(idx + 1, bit);
    }
    static boolean check(int bit) {
        Iterator<Integer> it = notBurger.iterator();

        while (it.hasNext()) {
            int cur = it.next();
            if((cur & bit) == cur) return false;
        }
        return true;
    }
}
