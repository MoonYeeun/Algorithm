package programmers;

import java.util.Arrays;
// 프로그래머스 lev2 구명보트
/*
정렬 후,
가장 작은 값 - 가장 큰 값 더한 값이 limit 보다 큰 경우 : 가장 큰 값은 무조건 혼자 타야함
= answer 증가, 가장 큰 값에서 idx 하나 줄임

가장 작은 값 - 가장 큰 값 limit 보다 작거나 같은 경우 : 같이 탈 수 있음
= answer 증가, 가장 큰 값 idx, 작은 값 idx 모두 줄임

큰 idx < 작 idx break

*/
public class Programmers_saveboat {
    public static void main(String[] args) {
        int[] people = {70, 50, 80, 50};
        //int[] people = {70, 80, 50};
        int limit = 100;
        int answer = 0;
        Arrays.sort(people);
        int min = 0;
        int max = people.length-1;
        while (true){
            if(max < min) break;
            if(people[min] + people[max] > limit){
                answer++;
                max--;
                continue;
            } else if(people[min] + people[max] <= limit){
                answer++;
                max--;
                min++;
                continue;
            }
        }
        System.out.println(answer);
    }
}
