package programmers;

import java.util.*;

// 2021 카카오 블라인드 채용 - 메뉴리뉴얼
public class Programmers_menuRenewal {
    static HashMap<String, Integer> map;
    static int max;

    public static void main(String[] args) {
//        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2, 3, 4};
        var result = solution(orders, course);

        for (String str : result) {
            System.out.println(str);
        }
    }

    public static String[] solution(String[] orders, int[] course) {
        PriorityQueue<String> pq = new PriorityQueue<>();

        for (int i : course) {
            map = new HashMap<>();
            max = 0;

            for (String str : orders) {
                if (str.length() < i) continue;

                // 각 문자열 정렬
                char[] arr = str.toCharArray();
                Arrays.sort(arr);
                str = new String(arr);

                com(str, i, 0, "");
            }
            // 가장 많은 것만 고르기
            for (String key : map.keySet()) {
                if (map.get(key) == max && max >= 2) pq.add(key);
            }
        }

        String[] answer = new String[pq.size()];
        int idx = 0;
        while (!pq.isEmpty()) {
            answer[idx++] = pq.poll();
        }
        return answer;
    }

    public static void com(String str, int cnt, int idx, String cur) {
        if (cnt == 0) {
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            max = Math.max(max, map.get(cur));
            return;
        }
        if (idx >= str.length()) return;
        // 선택 o
        if (cnt > 0) com(str, cnt - 1, idx + 1, cur + str.charAt(idx));
        // 선택 x
        com(str, cnt, idx + 1, cur);
    }
}
