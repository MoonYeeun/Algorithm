package LeetCode;

import java.util.*;

// 투포인터
public class LC56_Merge_Intervals {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        //int[][] intervals = {{1, 4}, {4, 5}};
        int[][] result = merge(intervals);

        for(int i = 0 ; i < result.length ; i++) {
            System.out.println(result[i][0] + " " + result[i][1]);
        }
    }
    static public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return intervals;

        Arrays.sort(intervals, (v1, v2) -> {
            if(v1[0] == v2[0]) return Integer.compare(v1[1], v2[1]);
            else return Integer.compare(v1[0], v2[0]);
        });

        LinkedList<int[]> list = new LinkedList<>();

        for(int[] cur : intervals) {
            // 범위 내에 있는 경우
            if(!list.isEmpty() && (list.getLast()[0] <= cur[0] && list.getLast()[1] >= cur[0])) {
                list.getLast()[1] = Math.max(list.getLast()[1], cur[1]);
            }
            else {
                list.add(cur);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
