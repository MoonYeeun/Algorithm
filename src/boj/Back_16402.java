package boj;

import java.util.*;

// 백준 16402 제국
// 유니온파인드
public class Back_16402 {
    static HashMap<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int kingdom = sc.nextInt();
        int war = sc.nextInt();
        sc.nextLine(); // 개행문자 제거

        for (int i = 0; i < kingdom; i++) {
            String str = sc.nextLine();
            map.put(str, str);
        }

        for (int i = 0; i < war; i++) {
            String[] result = sc.nextLine().split(",");

            String root1 = find(result[0]);
            String root2 = find(result[1]);

            if (Integer.parseInt(result[2]) == 1) {
                // 같은 종주국 내에서 속국 승리한 경우
                if (root1.equals(root2) && !root1.equals(result[0])) {
                    map.put(root2, result[0]);
                    map.put(result[0], result[0]);
                } else map.put(root2, root1); // 다른 왕국,속국 사이의 전쟁
            } else {
                // 같은 종주국 내에서 속국 승리한 경우
                if (root1.equals(root2) && !root2.equals(result[1])) {
                    map.put(root1, result[1]);
                    map.put(result[1], result[1]);
                } else map.put(root1, root2); // 다른 왕국,속국 사이의 전쟁
            }
        }

        Iterator<String> it = map.keySet().iterator();
        PriorityQueue<String> pq = new PriorityQueue<>(); // 정렬
        while (it.hasNext()) {
            String cur = it.next();
            if (map.get(cur).equals(cur)) pq.add(cur);
        }

        System.out.println(pq.size());
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }

    static String find(String king) {
        if (map.get(king).equals(king)) return king;
        else {
            map.put(king, find(map.get(king)));
            return map.get(king);
        }
    }
}
