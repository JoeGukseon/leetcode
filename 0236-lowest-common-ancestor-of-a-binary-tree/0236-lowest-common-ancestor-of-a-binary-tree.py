# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def lowestCommonAncestor(self, root, p, q):
        """
        :type root: TreeNode
        :type p: TreeNode
        :type q: TreeNode
        :rtype: TreeNode
        """
        if not root or root == p or root == q:
            return root

        # 왼쪽 서브트리에서 LCA 찾기
        left = self.lowestCommonAncestor(root.left, p, q)
        # 오른쪽 서브트리에서 LCA 찾기
        right = self.lowestCommonAncestor(root.right, p, q)

        if left and right:
            return root

        return left if left else right