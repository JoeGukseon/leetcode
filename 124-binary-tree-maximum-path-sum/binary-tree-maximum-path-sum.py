# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def __init__(self):
            self.max_sum = float('-inf')
    def maxPathSum(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        self.calculate_max_path_sum(root)
        return self.max_sum

    def calculate_max_path_sum(self, node):
        if not node:
            return 0
        
        left_max = max(0, self.calculate_max_path_sum(node.left))

        right_max = max(0, self.calculate_max_path_sum(node.right))

        current_max = node.val + left_max + right_max

        self.max_sum = max(self.max_sum, current_max)

        return node.val + max(left_max, right_max)