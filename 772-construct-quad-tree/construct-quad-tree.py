"""
# Definition for a QuadTree node.
class Node(object):
    def __init__(self, val, isLeaf, topLeft, topRight, bottomLeft, bottomRight):
        self.val = val
        self.isLeaf = isLeaf
        self.topLeft = topLeft
        self.topRight = topRight
        self.bottomLeft = bottomLeft
        self.bottomRight = bottomRight
"""

class Solution(object):
    def construct(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: Node
        """
        n = len(grid)
        return self.buildQuadTree(grid, 0, 0, n - 1, n - 1)

    def buildQuadTree(self, grid, rowStart, colStart, rowEnd, colEnd):
        if rowStart == rowEnd and colStart == colEnd:
            return Node(grid[rowStart][colStart] == 1, True, None, None, None, None)

        rowMid = (rowStart + rowEnd) // 2
        colMid = (colStart + colEnd) // 2

        topLeft = self.buildQuadTree(grid, rowStart, colStart, rowMid, colMid)
        topRight = self.buildQuadTree(grid, rowStart, colMid + 1, rowMid, colEnd)
        bottomLeft = self.buildQuadTree(grid, rowMid + 1, colStart, rowEnd, colMid)
        bottomRight = self.buildQuadTree(grid, rowMid + 1, colMid + 1, rowEnd, colEnd)

        if topLeft.isLeaf and topRight.isLeaf and bottomLeft.isLeaf and bottomRight.isLeaf and \
                topLeft.val == topRight.val == bottomLeft.val == bottomRight.val:
            return Node(topLeft.val, True, None, None, None, None)
        else:
            return Node(True, False, topLeft, topRight, bottomLeft, bottomRight)
