package programmers;

import java.util.PriorityQueue;

//프로그래머스 더맵게
public class Programmers_morespicy {
    public static void main(String[] args) {
        int[] scovile = {1,2,3,9,10,12};
        int k = 7;
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i : scovile){
            pq.add(i);
        }

        while(true){
            //모든 음식의 스코빌 지수가 k 이상 일 때
            if(pq.peek() >= k)
                break;
            //모든 음식의 스코빌 지수를 k 이상으로 만들 수 없을 때
            if(pq.size() == 1){
                answer = -1;
                break;
            }
            int not_spicy1 = pq.poll(); // 가장 맵지 않은 음식
            int not_spicy2 = pq.poll(); // 두번쨰로 맵지 않은 음식
            int mix_scovile = not_spicy1 + (not_spicy2*2);
            pq.add(mix_scovile);
            answer++;
        }
        System.out.println(answer);
    }
}
