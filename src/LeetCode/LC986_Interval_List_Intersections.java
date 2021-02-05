package LeetCode;

import java.util.*;

// 986. Interval List Intersections
public class LC986_Interval_List_Intersections {
    public static void main(String[] args) {
        int[][] firstList = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] secondList = {{3, 10}};

        int[][] ans = intervalIntersection(firstList, secondList);
        for (int[] cur : ans) {
            System.out.println(cur[0] + " " + cur[1]);
        }
    }

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int[][] ans = {};

        if (firstList.length == 0 || secondList.length == 0) return ans;

        ArrayList<int[]> result = new ArrayList<>();
        ArrayList<int[]> list = new ArrayList<>();

        // 두 개의 배열 하나로 합친 후 정렬
        list.addAll(arrToList(firstList));
        list.addAll(arrToList(secondList));

        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                if (p1[0] == p2[0]) return p1[1] - p2[1];
                return p1[0] - p2[0];
            }
        });

        // 겹치는 부분 계산
        int e = -1;
        for (int[] cur : list) {
            if (cur[1] <= e) {
                result.add(cur);
                continue;
            } else if (cur[0] <= e) {
                int[] newArr = {cur[0], e};
                result.add(newArr);
            }

            e = cur[1];
        }
        return result.toArray(new int[0][]);
    }

    static ArrayList<int[]> arrToList(int[][] list) {
        ArrayList<int[]> result = new ArrayList<>();

        for (int[] cur : list) {
            result.add(cur);
        }
        return result;
    }
}
