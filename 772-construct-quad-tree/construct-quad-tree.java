/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
};
*/

class Solution {
    public Node construct(int[][] grid) {
        int n = grid.length;
        return buildQuadTree(grid, 0, 0, n - 1, n - 1);
    }

    private Node buildQuadTree(int[][] grid, int rowStart, int colStart, int rowEnd, int colEnd) {
        if (rowStart == rowEnd && colStart == colEnd) {
            return new Node(grid[rowStart][colStart] == 1, true, null, null, null, null);
        }

        int rowMid = (rowStart + rowEnd) / 2;
        int colMid = (colStart + colEnd) / 2;

        Node topLeft = buildQuadTree(grid, rowStart, colStart, rowMid, colMid);
        Node topRight = buildQuadTree(grid, rowStart, colMid + 1, rowMid, colEnd);
        Node bottomLeft = buildQuadTree(grid, rowMid + 1, colStart, rowEnd, colMid);
        Node bottomRight = buildQuadTree(grid, rowMid + 1, colMid + 1, rowEnd, colEnd);

        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
            && topLeft.val == topRight.val && topRight.val == bottomLeft.val && bottomLeft.val == bottomRight.val) {
            return new Node(topLeft.val, true, null, null, null, null);
        } else {
            return new Node(true, false, topLeft, topRight, bottomLeft, bottomRight);
        }
    }
}