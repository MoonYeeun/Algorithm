package boj;

import java.util.*;

// 백준 6416 트리인가?
public class Back_6416 {
    static HashMap<Integer, Integer> tree;
    static HashSet<Integer> node;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = 0;

        while (true) {
            boolean end = false;
            boolean flag = false;
            tree = new HashMap<>();
            node = new HashSet<>();
            int edge = 0;
            k++;

            while (true) {
                int u = sc.nextInt();
                int v = sc.nextInt();

                if(u == -1 && v == -1) {
                    end = true;
                    break;
                }
                if(u == 0 && v == 0) break;

                if(find(u, v)) {
                    edge++;
                    node.add(u);
                    node.add(v);
                }
                else flag = true;
            }
            if(end) break;

            if(flag || node.size() != 0 && node.size() -1 != edge)
                System.out.println("Case " + k + " is not a tree.");
            else System.out.println("Case " + k + " is a tree.");
        }
    }
    static boolean find(int u, int v) {
        if(tree.containsKey(v)) return false;

        tree.put(v, u); // u의 루트 v
        return true;
    }
}
