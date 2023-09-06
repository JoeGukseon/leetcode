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
    int idx = 0;
    int answer = 0;
    public int kthSmallest(TreeNode root, int k) {
        dfs(root,k);
        return answer;
    }

    public void dfs(TreeNode curNode, int k) {
        if (curNode == null) {
            
            return;
        }

        dfs(curNode.left, k);

        idx++;
        if (idx == k) {
            answer = curNode.val;
        }
        dfs(curNode.right, k);
    }
}