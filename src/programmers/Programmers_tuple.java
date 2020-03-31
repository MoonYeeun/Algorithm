package programmers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;

// 2019 카카오 인턴 모의고사 튜플
public class Programmers_tuple {
    public static void main(String[] args) {
        //String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        //String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
        //String s = "{{123}}";
        String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";

        LinkedHashSet<Integer> set = new LinkedHashSet<>();

        s = s.replace("},{", "/");

        String[] tuple = s.split("[{{}}/]");

        Arrays.sort(tuple, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.length() - t1.length();
            }
        });

        for(String str : tuple) {
            if(str.equals("")) continue;
            String[] temp = str.split(",");

            for(String ss : temp) {
                set.add(Integer.parseInt(ss));
            }
        }

        int[] answer = new int[set.size()];
        int i = 0;
        Iterator<Integer> it = set.iterator();

        while (it.hasNext()) {
            answer[i++] = it.next();
        }
    }
}
