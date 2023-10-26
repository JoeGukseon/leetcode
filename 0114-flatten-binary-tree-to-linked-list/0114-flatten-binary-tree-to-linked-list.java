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
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        // 왼쪽 서브트리를 오른쪽으로 이동
        if (root.left != null) {
            flatten(root.left);

            TreeNode temp = root.right;
            root.right = root.left;
            root.left = null;

            // 오른쪽으로 이동한 노드의 마지막에 오른쪽 서브트리를 연결
            while (root.right != null) {
                root = root.right;
            }
            root.right = temp;
        }

        // 오른쪽 서브트리를 펼치기
        flatten(root.right);
    }
}