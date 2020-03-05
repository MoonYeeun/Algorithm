package programmers;

//2018 카카오 3차 방금그곡
public class Programmers_thatsong1 {
    public static void main(String[] args) {
        //String m = "ABCDEFG";
        String m = "CC#BCC#BCC#BCC#B";
        //String m = "ABC";
        //String m = "cdcdf";
        //String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String[] musicinfos = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        //String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        //String[] musicinfos = {"03:00,03:10,FOO,cdcdcdf"};

        String answer = "(None)";
        int maxtime = 0;
        // 문자와 혼동을 피하기 위해서 '대문자#'을 '소문자' 로 변경하여 스트링 비교
        m = m.replace("C#", "c").replace("D#", "d").replace("F#", "f").replace("A#", "a").replace("G#","g");
        for(String str : musicinfos) {

            String[] temp = str.split(",");
            String song = temp[3].replace("C#", "c").replace("D#", "d").replace("F#", "f").replace("A#", "a").replace("G#","g");
            System.out.println(song);
            int song_len = song.length();
            int playtime = getTime(temp[0], temp[1]); // 곡 재생시간

            String play_code = "";
            for(int j = 0 ; j < playtime ; j++) {
                play_code += song.charAt(j % song_len);
            }
            if(play_code.contains(m)) {
                if(maxtime < playtime) {
                    answer = temp[2];
                    maxtime = playtime;
                }
            }
        }
        System.out.println(answer);
    }
    // 곡 재생시간 구하는 함수
    public static int getTime(String a, String b) {
        String[] start = a.split(":");
        String[] end = b.split(":");

        int playtime = (Integer.parseInt(end[0]) - Integer.parseInt(start[0])) * 60
                + Integer.parseInt(end[1]) - Integer.parseInt(start[1]);
        return playtime;
    }
}
