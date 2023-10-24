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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }

        // 현재 preorder에서 루트를 가져와서 노드 생성
        TreeNode root = new TreeNode(preorder[preStart]);

        // inorder에서 루트의 위치 찾기
        int inIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
                break;
            }
        }

        // 왼쪽 서브트리를 재귀적으로 구성
        root.left = buildTreeHelper(preorder, inorder, preStart + 1, inStart, inIndex - 1);

        // 오른쪽 서브트리를 재귀적으로 구성
        root.right = buildTreeHelper(preorder, inorder, preStart + inIndex - inStart + 1, inIndex + 1, inEnd);

        return root;
    }
}