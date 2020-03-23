package programmers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// 2018 카카오 1차 추석트래픽
public class Programmers_holidayTraffic {
    public static void main(String[] args) {
//        String[] lines = {"2016-09-15 01:00:04.001 2.0s",
//                "2016-09-15 01:00:07.000 2s"};
//        String[] lines = {"2016-09-15 01:00:04.002 2.0s",
//                "2016-09-15 01:00:07.000 2s"};
        String[] lines = {"2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"};
        //String[] lines = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
        //String[] lines = {"2016-09-15 00:00:00.000 2.3s", "2016-09-15 23:59:59.999 0.1s"};

        ArrayList<pair> list = new ArrayList<>(); // 로그 리스트
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        for(int i = 0; i < lines.length ; i++) {
            try {
                String[] log = lines[i].split(" ");
                Date date = sdf.parse(log[0] + " " + log[1]);
                // 밀리 세컨즈 단위까지 변환
                long end_ms = date.getTime();
                double time = Double.parseDouble(log[2].substring(0, log[2].length()-1)) * 1000;
                long start_ms = end_ms - (long)time + 1;

                list.add(new pair(start_ms, end_ms));
            } catch (Exception e) {
            }
        }

        int answer = 0;
        for(int i = 0 ; i < list.size(); i++) {
            int cnt = 0;
            long f = list.get(i).end_ms + 999; // 응답완료 시간 + 1초
            long i_e = list.get(i).end_ms; // 기준시간 끝

            for(int j = 0 ; j < list.size() ; j++) {
                long j_s = list.get(j).start_ms; // 비교시간 시작
                long j_e = list.get(j).end_ms; // 비교시간 끝

                if(j_s > f) continue;
                if(((j_s >= i_e) && (j_s <= f))||
                ((j_e >= i_e) && (j_e <= f)) ||
                        ((j_s <= i_e) && (j_e >= i_e)))
                    cnt++;

            }
            answer = Math.max(answer, cnt);
        }
        System.out.println(answer);
    }
    static class pair {
        long start_ms;
        long end_ms;

        pair(long start_ms, long end_ms) {
            this.start_ms = start_ms;
            this.end_ms = end_ms;
        }
    }
}
