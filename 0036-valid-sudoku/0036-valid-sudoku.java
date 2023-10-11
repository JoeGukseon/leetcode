class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!isValidRow(board, i) || !isValidColumn(board, i) || !isValidBox(board, 3 * (i / 3), 3 * (i % 3))) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidRow(char[][] board, int row) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != '.' && !set.add(board[row][i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidColumn(char[][] board, int col) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (board[i][col] != '.' && !set.add(board[i][col])) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidBox(char[][] board, int startRow, int startCol) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] != '.' && !set.add(board[startRow + i][startCol + j])) {
                    return false;
                }
            }
        }
        return true;
    }
}