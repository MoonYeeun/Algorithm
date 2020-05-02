package programmers;

import java.util.*;

// 2018 카카오 뉴스클러스터링
public class Programmers_newsClustering {
    static HashMap<String, Integer> map1;
    static HashMap<String, Integer> map2;
    static String alpabet = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args) {
//        String str1 = "FRANCE";
//        String str2 = "french";
//        String str1 = "handshake";
//        String str2 = "shake hands";
        String str1 = "ac";
        String str2 = "bd";
//        String str1 = "fafafafa";
//        String str2 = "fafafa";

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        map1 = new HashMap<>();
        map2 = new HashMap<>();

        makeSet(str1, map1);
        makeSet(str2, map2);

        Iterator<String> it1 = map1.keySet().iterator();
        Iterator<String> it2 = map2.keySet().iterator();

        int inter = 0;
        int union = 0;

        // 교집합 & 합집합 만들기
        while (it1.hasNext()) {
            String str = it1.next();

            if(map2.containsKey(str)) {
                inter += Math.min(map1.get(str), map2.get(str));
                union += Math.max(map1.get(str), map2.get(str));
            }
            else union += map1.get(str);
        }
        // 합집합 만들기
        while (it2.hasNext()) {
            String str = it2.next();

            if(!map1.containsKey(str))
                union += map2.get(str);
        }

        if(union == 0) System.out.println(65536);
        else System.out.println((int)((double)(inter / union) * 65536));
    }
    static void makeSet(String str, HashMap<String, Integer> map) {
        String temp = "";
        for(int i = 0 ; i < str.length()-1 ; i++) {
            temp = str.substring(i, i+2);
            if(!alpabet.contains(String.valueOf(temp.charAt(0))) ||
                    !alpabet.contains(String.valueOf(temp.charAt(1))))
                continue;

            map.put(temp, map.getOrDefault(temp, 0)+1);
        }
    }
}
