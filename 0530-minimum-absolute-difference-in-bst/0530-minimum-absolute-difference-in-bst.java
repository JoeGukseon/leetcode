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
    int minDiff = Integer.MAX_VALUE;
    TreeNode prevNode;

    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return minDiff;
    }

    public void dfs(TreeNode curNode)
    {
        if (curNode != null) {
            dfs(curNode.left);
            if (prevNode != null) {
                minDiff = Math.min(minDiff, Math.abs(curNode.val - prevNode.val));
            }
            prevNode = curNode;
            dfs(curNode.right);
        }
    }
}