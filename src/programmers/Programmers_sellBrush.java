package programmers;

import java.util.*;

// 프로그래머스 2021 Dev Matching - 다단계 칫솔판매
public class Programmers_sellBrush {
    static int[] root;
    static int[] total;
    static HashMap<String, Integer> map;

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        int[] result = solution(enroll, referral, seller, amount);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        root = new int[enroll.length];
        total = new int[enroll.length];
        map = new HashMap<>();

        int idx = 0;
        for (String str : enroll) {
            map.put(str, idx++);
        }

        for (int i = 0; i < referral.length; i++) {
            root[i] = i;
        }

        for (int i = 0; i < referral.length; i++) {
            if (referral[i].equals("-")) continue;
            root[map.get(enroll[i])] = map.get(referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            divideProfit(map.get(seller[i]), amount[i] * 100);
        }

        return total;
    }

    public static void divideProfit(int x, int price) {
        int profit = (int) (price * 0.1);
        if (profit < 1) {
            total[x] += price;
            return;
        }
        total[x] += price - profit;

        if (root[x] == x) return;
        divideProfit(root[x], profit);
    }
}
