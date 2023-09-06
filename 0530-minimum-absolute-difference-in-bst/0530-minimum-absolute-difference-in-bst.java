/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private static final int BOUND = 1000000;
    public int getMinimumDifference(TreeNode root) {
        return dfs(root, -BOUND, BOUND);
    }

    private static int dfs(TreeNode node, int lower, int upper) {
        if (node == null) return BOUND;

        int cur = Math.min(node.val - lower, upper - node.val);
        int answer = Math.min(dfs(node.left, lower, node.val), dfs(node.right, node.val, upper));
        return Math.min(cur, answer);
    }
}