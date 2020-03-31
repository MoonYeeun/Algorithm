package programmers;

import java.util.PriorityQueue;

// 프로그래머스 여행경로 - 비트마스킹
public class Programmers_travelroute_1 {
    static PriorityQueue<String> pq = new PriorityQueue<>(); // 경로 중 알파벳 빠른 것 찾기 위함
    static int visit;
    public static void main(String[] args) {
        String[][] tickets = {{"ICN","SFO"},{"ICN","ATL"},{"SFO","ATL"},
                {"ATL","ICN"},{"ATL","SFO"}};
        //String[][] tickets = {{"ICN","JFK"},{"HND","IAD"},{"JFK","HND"}};

        for(int i = 0 ; i < tickets.length ; i++) {
            visit += (1<<i);
        }

        for(int i = 0 ; i < tickets.length ; i++) {
            if(tickets[i][0].equals("ICN"))
                dfs(tickets, tickets[i][1], (1<<i), "ICN");
        }
        String result = pq.peek();
        String[] answer = result.split("/");
        for(String str : answer) {
            System.out.println(str);
        }
    }
    static void dfs(String[][] tickets, String dep, int bit, String city) {
        city = city + "/";

        if(bit == visit) {
            pq.add(city + dep);
            return;
        }

        for(int i = 0 ; i < tickets.length ; i++) {
            if(!tickets[i][0].equals(dep) || ((1<<i) & bit) == (1<<i)) continue;

            dfs(tickets, tickets[i][1], bit | (1<<i), city+tickets[i][0]);
        }
    }
}
