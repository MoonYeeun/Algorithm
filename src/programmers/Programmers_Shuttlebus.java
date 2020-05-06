package programmers;

import java.util.*;

// 2018 카카오 1차 셔틀버스
public class Programmers_Shuttlebus {
    public static void main(String[] args) {
        int n = 5;
        int t = 1;
        int m = 5;
        String[] timetable = {"08:00", "08:01", "08:02", "08:03"};

//        int n = 1;
//        int t = 1;
//        int m = 1;
//        String[] timetable = {"23:59"};

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(String people : timetable) {
            String[] time = people.split(":");
            // 분 단위로 바꿈
            int arr = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            pq.add(arr);
        }
        // 셔틀버스 운행
        int bus = 9 * 60; // 셔틀 처음 운행 시간 : 9시
        int last = 0; // 마지막으로 탄 사람
        int remain = 0; // 남아 있는 자리
        int answer = 0;

        for(int i = 0 ; i < n ; i++) {
            remain = m;

            while (remain > 0) {
                if(pq.isEmpty()) break;
                if(pq.peek() > bus) break;

                last = pq.poll();
                remain--;
            }
            if(i == n-1) break;
            bus += t;
        }
        if(remain == 0) answer = last -1;
        if(remain > 0) answer = bus;

        System.out.println(setTime(answer));
    }
    static String setTime(int time) {
        int hour = (int)time / 60;
        int min = (int)time % 60;
        return String.format("%02d:%02d",hour, min);
    }
}
