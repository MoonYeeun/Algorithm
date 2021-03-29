package LeetCode;

import java.util.*;

// 347. Top K Frequent Elements
public class LC347_Top_K_Frequent_Elements {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = topKFrequent(nums, k);

        for (int num : result) {
            System.out.println(num);
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((prev, cur) -> cur[1] - prev[1]);

        for (int key : map.keySet()) {
            queue.add(new int[]{key, map.get(key)});
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll()[0];
        }
        return ans;
    }
}
