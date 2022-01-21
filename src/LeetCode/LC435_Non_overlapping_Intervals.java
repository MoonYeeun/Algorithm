package LeetCode;

import java.util.Arrays;
import java.util.Comparator;

// sort
public class LC435_Non_overlapping_Intervals {
    public static void main(String[] args) {
        //int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        int[][] intervals = {{-52, 31}, {-73, -26}, {82, 97}, {-65, -11}, {-62, -49}, {95, 99}
                , {58, 95}, {-31, 49}, {66, 98}, {-63, 2}, {30, 47}, {-40, -26}};
        int ans = eraseOverlapIntervals(intervals);
        System.out.println(ans);
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        int ans = 0;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[1] == arr2[1]) return arr2[0] - arr1[0];
                return arr1[1] - arr2[1];
            }
        });

        int loc = intervals[0][0];
        for (int[] arr : intervals) {
            if (arr[0] < loc) {
                ans++;
                continue;
            }
            loc = arr[1];
        }
        return ans;
    }
}
