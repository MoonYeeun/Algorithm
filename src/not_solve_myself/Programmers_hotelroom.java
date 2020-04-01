package not_solve_myself;

import java.util.HashSet;
import java.util.TreeSet;

// 2019 카카오 인턴 모의고사 호텔방배정
// treeset !
public class Programmers_hotelroom {
    static HashSet<Long> room = new HashSet<>();
    static TreeSet<Long> empty_room = new TreeSet<>();
    public static void main(String[] args) {
        long k = 10;
        long[] room_number = {1, 3, 4, 1, 3, 1};

        long[] answer = new long[room_number.length];
        int idx = 0;
        for(long i : room_number) {
            answer[idx++] = findRoom(i);
        }

        for(long i : answer) {
            System.out.println(i);
        }
    }
    static long findRoom(long num) {
        // 이미 해당 방이 배정되어 있는 경우
        if(room.contains(num)) return findRoom(empty_room.higher(num));

        room.add(num);
        empty_room.remove(num);
        // 배정된 방 보다 +1한 방이 배정되지 않았다면 빈방으로 지정
        if(!room.contains(num+1)) empty_room.add(num+1);
        return num;
    }
}
