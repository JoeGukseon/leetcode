# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def flatten(self, root):
        """
        :type root: TreeNode
        :rtype: None Do not return anything, modify root in-place instead.
        """
        if not root:
            return None

        # 왼쪽 서브트리를 오른쪽으로 이동
        if root.left:
            self.flatten(root.left)

            temp = root.right
            root.right = root.left
            root.left = None

            # 오른쪽으로 이동한 노드의 마지막에 오른쪽 서브트리를 연결
            while root.right:
                root = root.right
            root.right = temp

        # 오른쪽 서브트리를 펼치기
        self.flatten(root.right)