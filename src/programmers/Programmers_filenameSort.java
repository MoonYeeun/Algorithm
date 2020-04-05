package programmers;

import java.util.PriorityQueue;

// 2018 카카오 3차 파일명정렬
public class Programmers_filenameSort {
    public static void main(String[] args) {
        //String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        //String[] files = {"muzi1.txt", "MUZI1.txt", "muzi001.txt", "muzi1.TXT"};

        PriorityQueue<pair> pq = new PriorityQueue<>();

        for(int i = 0 ; i < files.length ; i++) {
            String head = "";
            int idx = 0;
            // head 구하기
            for(int j = 0 ; j < files[i].length() ; j++) {
                if(Character.isDigit(files[i].charAt(j))) {
                    head = files[i].substring(0, j);
                    idx = j;
                    break;
                }
            }
            // number 구하기
            int end = files[i].length() > idx + 5 ? idx + 5 : files[i].length();
            int num = 0;
            boolean flag = true;
            for(int j = idx ; j < end ; j++) {
                if(!Character.isDigit(files[i].charAt(j))) {
                    num = Integer.parseInt(files[i].substring(idx, j));
                    flag = false;
                    break;
                }
            }
            if(flag) num = Integer.parseInt(files[i].substring(idx, end));
            pq.add(new pair(i, head.toUpperCase(), num, files[i]));
        }

        String[] answer = new String[pq.size()];
        int idx = 0;
        while (!pq.isEmpty()) {
            answer[idx++] = pq.poll().fileName;
        }
        for(String str : answer) {
            System.out.println(str);
        }
    }
    static class pair implements Comparable<pair> {
        int idx, num;
        String head, fileName;

        pair(int idx, String head, int num, String fileName) {
            this.idx = idx;
            this.head = head;
            this.num = num;
            this.fileName = fileName;
        }

        @Override
        public int compareTo(pair pair) {
            if(this.head.equals(pair.head))
                if(this.num == pair.num)
                    return this.idx - pair.idx;
                else
                    return this.num - pair.num;
            return this.head.compareTo(pair.head);
        }
    }
}
