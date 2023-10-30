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
    public int sumNumbers(TreeNode root) {
        return sumNumbersHelper(root, 0);
    }

    private int sumNumbersHelper(TreeNode node, int currentSum) {
        if (node == null) {
            return 0;
        }

        // 현재 경로의 숫자 업데이트
        currentSum = currentSum * 10 + node.val;

        // 리프 노드인 경우, 현재 경로의 숫자를 반환
        if (node.left == null && node.right == null) {
            return currentSum;
        }

        // 좌우 서브트리의 합을 재귀적으로 계산하여 더함
        return sumNumbersHelper(node.left, currentSum) + sumNumbersHelper(node.right, currentSum);
    }
}