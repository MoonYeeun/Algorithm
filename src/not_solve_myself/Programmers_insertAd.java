package not_solve_myself;

// 2021 카카오 광고삽입
// 1. 시간 -> 초 / 초 -> 시간포맷 으로 바꾸기
// 2. 시청자 나가고 들어오는 구간 체크
// 3. 누적합 구하기
// 4. 누적합 바탕으로 시청자 최대 구간 구하기
public class Programmers_insertAd {
    public static void main(String[] args) {
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};

        System.out.println(solution(play_time, adv_time, logs));
    }

    public static String solution(String play_time, String adv_time, String[] logs) {
        long[] total = new long[360000];

        if (play_time.equals(adv_time)) return "00:00:00";

        int playTime = convertToSec(play_time);
        int advTime = convertToSec(adv_time);

        // 시청자 재생 시간 체크
        for (String log : logs) {
            String[] time = log.split("-");
            int start = convertToSec(time[0]);
            int end = convertToSec(time[1]);

            total[start] += 1;
            total[end] -= 1;
        }

        // 시간별 재생수 구하기
        for (int i = 1; i < playTime; i++) {
            total[i] += total[i - 1];
        }
        // 누적합 구하기
        for (int i = 1; i < playTime; i++) {
            total[i] += total[i - 1];
        }

        long max = total[advTime];
        int ans = 0;

        for (int i = advTime + 1; i < playTime; i++) {
            long sum = total[i] - total[i - advTime];

            if (sum > max) {
                max = sum;
                ans = i - advTime + 1;
            }
        }
        return convertToString(ans);
    }

    public static int convertToSec(String time) {
        String[] arr = time.split(":");
        return Integer.parseInt(arr[0]) * 3600 + Integer.parseInt(arr[1]) * 60 + Integer.parseInt(arr[2]);
    }

    public static String convertToString(int time) {
        int hour = time / 3600;
        int min = (time % 3600) / 60;
        int sec = time % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
}
