package LeetCode;

// 중위순회하면서 이전 - 현재 값 비교 -> 가장 차이가 작은 값 구하기
public class LC530_Minimum_Absolute_Difference_in_BST {
    static int ans;
    static TreeNode prev;

    public static void main(String[] args) {

    }

    public static int getMinimumDifference(TreeNode root) {
        ans = Integer.MAX_VALUE;

        inOrder(root);
        return ans;
    }

    public static void inOrder(TreeNode root) {
        if (root == null) return;

        inOrder(root.left);
        ans = prev == null ? ans : Math.min(ans, Math.abs(root.val - prev.val));

        prev = root;
        inOrder(root.right);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
