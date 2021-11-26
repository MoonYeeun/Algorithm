package LeetCode;

import java.util.*;

// ⭐️ 763. Partition Labels
// 투포인터
public class LC763_Partition_Labels {
    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        List<Integer> result = partitionLabels(s);
    }

    public static List<Integer> partitionLabels(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();

        // 각 알파벳의 마지막 인덱스 기록
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        int start = -1;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            end = Math.max(end, map.get(c));

            if (i == end) {
                ans.add(end - start);
                start = i;
            }
        }

        return ans;
    }
}
