# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def sumNumbers(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        return self.sumNumbersHelper(root, 0)

    def sumNumbersHelper(self, node, currentSum):
        if not node:
            return 0

        currentSum = currentSum * 10 + node.val

        if not node.left and not node.right:
            return currentSum

        return self.sumNumbersHelper(node.left, currentSum) + self.sumNumbersHelper(node.right, currentSum)
        