package LeetCode;

import java.util.*;

// 863. All Nodes Distance K in Binary Tree
// 1. 부모 노드 체킹
// 2. bfs로 해당 거리에 있는 노드 구하기
public class LC863_All_Nodes_Distance_K_in_Binary_tree {
    static List<Integer> ans;
    static HashMap<TreeNode, TreeNode> map;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        ans = new ArrayList<>();
        map = new HashMap<>();

        HashSet<Integer> visit = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();

        setParent(root);
        queue.add(target);

        int lev = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                TreeNode cur = queue.poll();

                if (cur == null || visit.contains(cur.val)) continue;

                visit.add(cur.val);

                if (lev == K) {
                    ans.add(cur.val);
                    continue;
                }

                queue.add(cur.left);
                queue.add(cur.right);
                queue.add(map.get(cur));
            }
            lev++;
        }

        return ans;
    }

    public void setParent(TreeNode cur) {
        if (cur == null) return;

        if (cur.left != null) map.put(cur.left, cur);
        if (cur.right != null) map.put(cur.right, cur);

        setParent(cur.left);
        setParent(cur.right);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
