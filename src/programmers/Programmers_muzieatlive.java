package programmers;
//프로그래머스 2019 카카오 블라인드 채용 / 무지의 먹방 라이브
public class Programmers_muzieatlive {
    public static void main(String[] args) {
        //int[] food_times = {3,1,2};
        //int[] food_times = {3,1,1,1,2,4,3};
        //int[] food_times = {4,3,5,6,2};
        int[] food_times = {4,1,1,5};
        //long k = 5;
        //ong k = 12;
        //long k = 7;
        long k = 7;
        int total = food_times.length;
        long turn = 0; //현재 먹을 음식 번호
        for(long i = 0 ; i <= k ; i++){
            if(food_times[(int)turn%total] == 0){
                int current = (int)turn%total;
                while(true){
                    turn++;
                    if(current == turn%total){
                        System.out.println(-1);
                        return;
                    }
                    if(food_times[(int)turn%total]!=0)
                        break;
                }
            }
            //System.out.println("현재 turn "+turn);
            food_times[(int)turn%total]--;
            if(i == k) continue;
            turn++;
        }
        int answer = ((int)turn%total)+1;
        System.out.println(answer);
    }
}
