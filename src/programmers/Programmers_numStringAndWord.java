package programmers;

import java.util.*;

// 프로그래머스 2021 카카오 인턴십 - 숫자 문자열과 영단어
public class Programmers_numStringAndWord {
    public static void main(String[] args) {
        String s = "one4seveneight";
//        String s = "23four5six7";
//        String s = "2three45sixseven";
//        String s = "123";
        System.out.println(solution(s));
    }

    // 1차 풀이
//    public static int solution(String s) {
//        HashMap<String, Integer> map = makeMap();
//        StringBuilder sb = new StringBuilder();
//        StringBuilder temp = new StringBuilder();
//        char[] word = s.toCharArray();
//
//        for(char c : word) {
//            if(!Character.isDigit(c)) {
//                temp.append(c);
//
//                if(map.containsKey(temp.toString())) {
//                    sb.append(map.get(temp.toString()));
//                    temp = new StringBuilder();
//                }
//            }
//
//            else {
//                sb.append(c);
//            }
//        }
//
//        return Integer.parseInt(sb.toString());
//    }
//    public static HashMap<String, Integer> makeMap() {
//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("zero", 0);
//        map.put("one", 1);
//        map.put("two", 2);
//        map.put("three", 3);
//        map.put("four", 4);
//        map.put("five", 5);
//        map.put("six", 6);
//        map.put("seven", 7);
//        map.put("eight", 8);
//        map.put("nine", 9);
//        return map;
//    }
    // 더 깔끔한 풀이 (다른 사람 풀이 참고)
    public static int solution(String s) {
        String[] str = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        for (int i = 0; i < str.length; i++) {
            s = s.replaceAll(str[i], Integer.toString(i));
        }

        return Integer.parseInt(s);
    }
}
