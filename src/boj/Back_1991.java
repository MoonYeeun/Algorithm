package boj;

import java.util.*;

// 백준 1991 트리 순회
public class Back_1991 {
    static char[][] tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        tree = new char[n][2];

        for (int i = 0; i < n; i++) {
            char root = sc.next().toCharArray()[0];
            char left = sc.next().toCharArray()[0];
            char right = sc.next().toCharArray()[0];

            tree[root - 'A'][0] = left;
            tree[root - 'A'][1] = right;
        }

        preOrder('A');
        System.out.println();
        inOrder('A');
        System.out.println();
        postOrder('A');
        System.out.println();
    }

    static void preOrder(char c) {
        if (c == '.') return;

        System.out.print(c);
        preOrder(tree[c - 'A'][0]);
        preOrder(tree[c - 'A'][1]);
    }

    static void inOrder(char c) {
        if (c == '.') return;

        inOrder(tree[c - 'A'][0]);
        System.out.print(c);
        inOrder(tree[c - 'A'][1]);
    }

    static void postOrder(char c) {
        if (c == '.') return;

        postOrder(tree[c - 'A'][0]);
        postOrder(tree[c - 'A'][1]);
        System.out.print(c);
    }
}
