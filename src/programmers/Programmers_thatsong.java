package programmers;

import java.util.PriorityQueue;

public class Programmers_thatsong {
    public static void main(String[] args) {
        //String m = "ABCDEFG";
        //String m = "CC#BCC#BCC#BCC#B";
        //String m = "ABC";
        String m = "cdcdf";
        String[] musicinfos = {"03:00,03:10,FOO,cdcdcdf"};
        //String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        //String[] musicinfos = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        //String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};

        // 문자와 혼동을 피하기 위해서 '대문자#'을 '소문자' 로 변경하여 스트링 비교
        m = m.replace("C#", "c").replace("D#", "d").replace("F#", "f").replace("A#", "a").replace("G#","g");
        int m_len = m.length();
        int musicinfos_len = musicinfos.length;
        PriorityQueue<pair> pq = new PriorityQueue<>();

        for(int i = 0 ; i < musicinfos_len ; i++) {
            boolean isSame = false; // 곡 일치 여부 판별
            String[] temp = musicinfos[i].split(",");
            temp[3]= temp[3].replace("C#", "c").replace("D#", "d").replace("F#", "f").replace("A#", "a").replace("G#","g");
            int song_len = temp[3].length();
            int idx = temp[3].indexOf(m.charAt(0)); // 문자열 m 이 시작하는 인덱스 찾기
            String[] start_time = temp[0].split(":");
            String[] end_time = temp[1].split(":");
            // 곡 재생시간
            int playtime = ((Integer.parseInt(end_time[0]) - Integer.parseInt(start_time[0])) * 60 + Integer.parseInt(end_time[1]))
                    - Integer.parseInt(start_time[1]);

            // 루프 돌면서 시작 인덱스 찾기
            while(idx != -1 && idx <= (playtime - m_len)){
                int start_idx = idx;
                for(int j = 0 ; j < m_len ; j++) {
                    if(m.charAt(j) != temp[3].charAt((start_idx++) % song_len)) {
                        isSame = false;
                        break;
                    } else isSame = true;
                }
                if(isSame) break;
                idx = temp[3].indexOf(m.charAt(0), idx+1);
            }
            if(isSame) pq.add(new pair(playtime, i, temp[2]));
        }
        if(pq.isEmpty()) System.out.println("(None)");
        else System.out.println(pq.poll().song);
    }
    static class pair implements Comparable<pair> {
        int playtime;
        int order;
        String song;

        pair (int playtime, int order, String song) {
            this.playtime = playtime;
            this.order = order;
            this.song = song;
        }
        @Override
        public int compareTo(pair pair) {
            if(pair.playtime == this.playtime)
                return pair.order < this.order ? 1 : -1;
            else
                return pair.playtime > this.playtime ? 1 : -1;
        }
    }
}
