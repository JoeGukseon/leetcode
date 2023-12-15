class Solution {
    int resultCount = 0;

    public int totalNQueens(int n) {
        int[] placement = new int[n];

        Arrays.fill(placement, -1);

        placeQueens(0, n, placement);

        return resultCount;
    }

    private void placeQueens(int row, int n, int[] placement) {
        if (row == n) {
            resultCount++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValidPlacement(row, col, placement)) {
                placement[row] = col;

                placeQueens(row + 1, n, placement);

                placement[row] = -1;
            }
        }
    }

    private boolean isValidPlacement(int row, int col, int[] placement) {
        for (int i = 0; i < row; i++) {
            if (placement[i] == col || Math.abs(placement[i] - col) == row - i) {
                return false;
            }
        }
        return true;
    }

}