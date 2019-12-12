package programmers;

import java.util.*;

//프로그래머스 여행경로
public class Programmers_travelroute {

    static boolean[] visit;
    static String str = "";
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        String[][] tickets = {{"ICN","SFO"},{"ICN","ATL"},{"SFO","ATL"},
                {"ATL","ICN"},{"ATL","SFO"}};
        //String[][] tickets = {{"ICN","JFK"},{"HND","IAD"},{"JFK","HND"}};

        visit = new boolean[tickets.length];
        for(int i = 0 ; i < tickets.length ; i++){
            if(tickets[i][0].equals("ICN")){
                visit[i] = true;
                str = tickets[i][0]+",";
                dfs(tickets,tickets[i][1],1);
                visit[i] = false;
            }
        }
        Collections.sort(list);
        String[] answer = list.get(0).split(",");
        for(String str : answer)
            System.out.println(str);
    }
    static void dfs(String[][] tickets,String target, int cnt){

        str += target+",";
        if(cnt == tickets.length){
            list.add(str);
            return;
        }
        for(int i = 0 ; i < tickets.length ; i++){
            if(tickets[i][0].equals(target) && !visit[i]){
                visit[i] = true;
                dfs(tickets,tickets[i][1],cnt+1);
                visit[i] = false;
                str = str.substring(0,str.length()-4); // (다른 사람 코드 참조)
            }

        }

    }
}
