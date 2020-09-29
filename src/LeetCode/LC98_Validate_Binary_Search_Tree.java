package LeetCode;

public class LC98_Validate_Binary_Search_Tree {
    public static void main(String[] args) {
        // input: [2,1,3]
        // output: true
        // input: [5,1,4,null,null,3,6]
        // output: false
    }

    public boolean isValidBST(TreeNode root) {
        return find(root, null, null);
    }

    static boolean find(TreeNode cur, Integer min, Integer max) {
        if (cur == null) return true;

        int value = cur.val;

        if (min != null && min >= value) return false;
        if (max != null && max <= value) return false;

        return find(cur.left, min, value) && find(cur.right, value, max);
    }

    // Definition for a binary tree node.
    public class TreeNode {
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
