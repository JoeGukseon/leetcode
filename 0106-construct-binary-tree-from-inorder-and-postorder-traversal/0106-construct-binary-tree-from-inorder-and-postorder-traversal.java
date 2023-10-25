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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 인덱스를 기준으로 재귀적으로 이진 트리를 구성
        return buildTreeHelper(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd) {
        // 범위를 벗어나면 null 반환
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        // 현재 서브트리의 루트는 후위 순회 배열의 마지막 요소
        int rootValue = postorder[postEnd];
        TreeNode root = new TreeNode(rootValue);

        // 중위 순회 배열에서 루트의 위치를 찾아왔다면, 그 위치를 기준으로 왼쪽과 오른쪽으로 나눈다.
        int rootIndexInInorder = findRootIndex(inorder, rootValue, inStart, inEnd);
        int leftSubtreeSize = rootIndexInInorder - inStart;

        // 왼쪽 서브트리 재귀적으로 구성
        root.left = buildTreeHelper(inorder, postorder, inStart, rootIndexInInorder - 1, postStart, postStart + leftSubtreeSize - 1);

        // 오른쪽 서브트리 재귀적으로 구성
        root.right = buildTreeHelper(inorder, postorder, rootIndexInInorder + 1, inEnd, postStart + leftSubtreeSize, postEnd - 1);

        return root;
    }

    private int findRootIndex(int[] inorder, int rootValue, int inStart, int inEnd) {
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootValue) {
                return i;
            }
        }
        return -1;
    }
}