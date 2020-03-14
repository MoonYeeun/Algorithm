package programmers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//2019 카카오 매칭 점수
public class Programmers_matchingScore {
    public static void main(String[] args) {
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};
        String word = "Muzi";

        int len = pages.length;
        word = word.toLowerCase();

        HashMap<pair, Integer> basic_score = new HashMap<>();
        HashMap<String, Double> link_score = new HashMap<>();

        PriorityQueue<matching> pq = new PriorityQueue<>();

        for(int i = 0 ; i < len ; i++) {
            pages[i] = pages[i].toLowerCase();
            int score = get_score(pages[i], word); // 기본 점수
            Queue<String> link_list = get_link(pages[i]); // 외부 링크
            int cnt = link_list.size(); // 외부 링크 수
            // url 구하기
            int index = pages[i].indexOf("meta property=\"og:url\"");
            String url = pages[i].substring(index+32);
            index = url.indexOf("\"/>");
            url = url.substring(0, index);

            basic_score.put(new pair(url, i), score);

            double match_score = (score*1.0) / cnt;
            while (!link_list.isEmpty()){
                String link = link_list.poll();
                link_score.put(link, link_score.getOrDefault(link, 0.0)+ match_score);
            }
        }

        for(pair key : basic_score.keySet()){
            int score1 = basic_score.get(key); // 기본 점수
            if(link_score.containsKey(key.url)) {
                double score2 = link_score.get(key.url); // 링크 점수
                pq.add(new matching(key.idx, score1+score2));
            }
            else pq.add(new matching(key.idx, score1));
        }
        System.out.println(pq.poll().idx);
    }
    // 기본 점수 구하기
    static int get_score(String page, String word) {
        page = page.substring(page.indexOf("<body>"), page.indexOf("</body>"));
        int score = 0;
        int word_len = word.length();
        int idx = 0;
        for(;idx < page.length();) {
            idx = page.indexOf(word,idx+1);
            if(idx == -1) break;
            if(!Character.isLetter(page.charAt(idx-1)) && !Character.isLetter(page.charAt(idx+word_len))) {
                score++;
                idx += word_len;
            }
        }
        return score;
    }
    // 외부 링크 구하기
    static Queue<String> get_link(String page) {
        Queue<String> link_list = new LinkedList<>();
        int start_idx = page.indexOf("<a href=");
        while(start_idx != -1) {
            int end_idx = page.indexOf(">",start_idx+1);
            String link = page.substring(start_idx+9, end_idx-1);
            link_list.add(link);

            start_idx = page.indexOf("<a href=", start_idx + 1);
        }
        return link_list;
    }
    static class pair {
        String url;
        int idx;

        pair(String url, int idx){
            this.url = url;
            this.idx = idx;
        }
    }
    static class matching implements Comparable<matching>{
        int idx;
        double score;

        matching(int idx, double score) {
            this.idx = idx;
            this.score = score;
        }

        @Override
        public int compareTo(matching matching) {
            if(this.score == matching.score)
                return this.idx > matching.idx ? 1 : -1;
            return matching.score > this.score ? 1 : -1;
        }
    }
}
