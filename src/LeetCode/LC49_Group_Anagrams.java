package LeetCode;

import java.util.*;

// 49. Group Anagrams
public class LC49_Group_Anagrams {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        groupAnagrams(strs);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String res = String.valueOf(array);

            if (!map.containsKey(res)) map.put(res, new ArrayList<>());
            map.get(res).add(str);
        }

        List<List<String>> ans = new LinkedList<>();
        for (String key : map.keySet()) {
            ans.add(map.get(key));
        }
        return ans;
    }
}
