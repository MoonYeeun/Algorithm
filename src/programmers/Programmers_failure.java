package programmers;


import java.util.PriorityQueue;

//프로그래머스 2019 kakao blind recruitment 실패율
public class Programmers_failure {
    public static void main(String[] args) {
        //int n = 5;
        int n = 4;
        //int[] stages = {2,1,2,6,2,4,3,3};
        int[] stages = {4,4,4,4,4};
        int[] doing = new int[n+2]; // 해당 스테이지 도전하는 사람 수
        //double[] failure = new double[n+1]; // 해당 스테이지 실패율
        int[] clear = new int[n+2]; // 해당 스테이지 클리어 한 사람 수
        PriorityQueue<pair> pq = new PriorityQueue<pair>();
        for(int i : stages){
            doing[i]++;
        }
        clear[n+1] = doing[n+1];
        for(int i = n ; i >= 1 ; i--){
            clear[i] = doing[i] + clear[i+1];
        }
        for(int i = 1 ; i <= n ; i++){
            if(clear[i]== 0){
                pq.add(new pair(i,0));
                continue;
            }
            pq.add(new pair(i,(double)doing[i]/(double)clear[i]));
        }
        while (!pq.isEmpty()){
            pair pair = pq.poll();
            System.out.println(pair.stage);
        }
    }
    static class pair implements Comparable<pair>{
        int stage;
        double failure;

        public pair(int stage,double failure){
            this.stage = stage;
            this.failure = failure;
        }
        @Override
        public int compareTo(pair pair) {
            if(pair.failure == this.failure)
                return pair.stage <= this.stage ? 1 : -1;
            else
                return pair.failure > this.failure ? 1 : -1;
        }
    }
}
