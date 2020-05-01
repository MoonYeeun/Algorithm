package programmers;

import java.util.*;

// 2018 카카오 1차 캐시
public class Programmers_cache {
    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul",
                "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
//        String[] cities = {"Jeju", "Pangyo", "Seoul", "Jeju",
//                "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};

        Queue<String> queue = new LinkedList<>();
        if(cacheSize == 0) System.out.println(cities.length * 5);

        int answer = 0;
        for(String city : cities) {
            city = city.toLowerCase();

            if(queue.contains(city)) {
                queue.remove(city);
                queue.add(city);
                answer += 1;
                continue;
            }

            if(queue.size() == cacheSize) queue.poll();
            queue.add(city);
            answer += 5;
        }
        System.out.println(answer);
    }
}
