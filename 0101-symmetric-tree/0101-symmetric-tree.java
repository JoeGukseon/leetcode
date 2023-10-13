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
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        //기저 조건은 둘다 null일 경우 더 탐색하지 않기에 return true
        if (t1 == null && t2 == null) {
            return true;
        }
        //둘중 하나만 null인 경우 대칭이 아니므로 false
        if (t1 == null || t2 == null) {
            return false;
        }

        //두 노드의 값을 비교 전부 같을 시에만 true 가 반환된다.
        return (t1.val == t2.val)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }
}