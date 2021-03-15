package LeetCode;

import java.util.*;

// 218. The Skyline Problem
// 각 지점 별 최고 높이 계산 (pq 활용)
// 높이 바뀌는 지점 : skyline
public class LC218_The_Skyline_Problem {
    public static void main(String[] args) {
        int[][] buildings = {{2, 9, 10}, {3, 7, 15},
                {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};

        List<List<Integer>> result = getSkyline(buildings);

        for (List<Integer> i : result) {
            for (int j : i) {
                System.out.println(j + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int[] building : buildings) {
            map.putIfAbsent(building[0], new ArrayList<>());
            map.putIfAbsent(building[1], new ArrayList<>());

            map.get(building[0]).add(building);
            map.get(building[1]).add(building);
        }

        Iterator<Integer> it = map.keySet().iterator();
        int prev = 0;
        while (it.hasNext()) {
            int key = it.next();
            int cur = 0;

            List<int[]> list = map.get(key);
            for (int[] i : list) {
                if (i[0] == key) pq.add(i[2]);
                else pq.remove(i[2]);
            }

            if (!pq.isEmpty()) cur = pq.peek();

            if (prev != cur) {
                List<Integer> newList = new ArrayList<>();
                newList.add(key);
                newList.add(cur);
                ans.add(newList);
            }
            prev = cur;
        }

        return ans;
    }
}
