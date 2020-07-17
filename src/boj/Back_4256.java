package boj;

import java.util.*;

// ⭐️ 백준 4256 트리
// 전위 중위 후위 순회
public class Back_4256 {
    static int[] preorder, inorder;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();

            preorder = new int[n+1]; // 가장 첫번째 값 항상 최상위 루트
            for(int i = 1 ; i <= n ; i++) {
                preorder[i] = sc.nextInt();
            }

            inorder = new int[n+1]; // preorder의 최상위 루트 값의 idx 기준으로 왼, 오른 트리로 나뉨
            for(int i = 1 ; i <= n ; i++) {
                inorder[sc.nextInt()] = i;
            }
            order(1, 1, n);
        }
    }
    static void order(int root, int l, int r) {
        if(l > r) return;
        int rootIdx = inorder[preorder[root]];

        // 왼쪽 탐색
        order(root + 1, l, rootIdx-1);
        // 오른쪽 탐색
        order(root + rootIdx - l + 1, rootIdx + 1, r);
        System.out.print(preorder[root] + " ");
    }
}
