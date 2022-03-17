package programmers;

import java.util.*;

// 2022 카카오 주차요금계산
public class Programmers_Cal_Parking_Fee {
    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        var result = solution(fees, records);
        for (int i : result) {
            System.out.println(i);
        }
    }

    static public int[] solution(int[] fees, String[] records) {
        HashMap<String, Integer> total = new HashMap<>(); // 차량별 총 이용시간 기록

        for (String record : records) {
            String[] parking = record.split(" ");

            int result = hourToMin(parking[0]);
            int prefix = parking[2].equals("IN") ? -1 : 1;

            total.put(parking[1], total.getOrDefault(parking[1], 0) + result * prefix);
        }
        return total.entrySet().stream().sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
                .mapToInt(e -> {
                    int time = e.getValue() <= 0 ? hourToMin("23:59") + e.getValue() : e.getValue();
                    return calParkingFee(time, fees);
                }).toArray();
    }

    static int hourToMin(String time) {
        String[] info = time.split(":");
        return (Integer.parseInt(info[0]) * 60) + Integer.parseInt(info[1]);
    }

    static int calParkingFee(int totalTime, int[] fees) {
        totalTime -= fees[0];
        if (totalTime <= 0) return fees[1];
        int extra = ((int) Math.ceil(totalTime / (fees[2] * 1.0))) * fees[3]; // 초과요금
        return fees[1] + extra;
    }
}
