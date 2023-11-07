"""
# Definition for a Node.
class Node(object):
    def __init__(self, val=0, left=None, right=None, next=None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
"""

class Solution(object):
    def connect(self, root):
        """
        :type root: Node
        :rtype: Node
        """
        if not root:
            return None

        queue = deque([root])

        while queue:
            level_size = len(queue)

            for i in range(level_size):
                current = queue.popleft()

                if i < level_size - 1:
                    current.next = queue[0]

                if current.left:
                    queue.append(current.left)

                if current.right:
                    queue.append(current.right)

        return root