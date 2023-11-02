# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def isValidBST(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        return self.is_valid_bst(root, float('-inf'), float('inf'))

    def is_valid_bst(self, node, min_val, max_val):
        if not node:
            return True

        if not min_val < node.val < max_val:
            return False

        return (self.is_valid_bst(node.left, min_val, node.val) and
                self.is_valid_bst(node.right, node.val, max_val))